import os
import signal
import subprocess

import boto3

import pytest

from pyspark.sql import SparkSession

S3_ACCESS_KEY='dummy-value'
S3_SECRET_KEY='dummy-value'
S3_ENDPOINT='http://127.0.0.1:5000'

@pytest.fixture(scope='module')
def spark(request: pytest.FixtureRequest) -> SparkSession:
    """Creates a spark intance to execute the job in test context

    Parameters:
    request (pytest.FixtureRequest): Object to request action to the test context

    Returns:
    SparkSession: Returns the spark session that was created
    
    """

    os.environ["PYSPARK_SUBMIT_ARGS"] = '--packages "org.apache.hadoop:hadoop-aws:2.7.3" pyspark-shell'

    process = subprocess.Popen('moto_server s3', \
        stdout=subprocess.PIPE, \
            shell=True, \
                preexec_fn=os.setsid)

    spark = SparkSession.builder.master('local')\
        .appName('testing-app-name')\
            .getOrCreate()

    hadoop = spark.sparkContext._jsc.hadoopConfiguration()
    hadoop.set('fs.s3a.access.key', S3_ACCESS_KEY)
    hadoop.set('fs.s3a.secret.key', S3_SECRET_KEY)
    hadoop.set('fs.s3a.endpoint', S3_ENDPOINT)
    # hadoop.set('fs.s3a.multipart.size', '104857600')
    # hadoop.set('fs.s3.impl', 'org.apache.hadoop.fs.s3a.S3AFileSystem')

    s3 = boto3.resource('s3', endpoint_url=S3_ENDPOINT)
    s3.create_bucket(Bucket='test-bucket')

    def teardown():
        os.killpg(os.getpgid(process.pid), signal.SIGTERM)
        spark.stop()

    request.addfinalizer(teardown)

    return spark
from pyspark.sql.session import SparkSession
import pytest
import glue_job as sut

import boto3


@pytest.fixture
def resource(request: pytest.FixtureRequest, spark: SparkSession):

    columns = ['endpoint', 'status', 'latency', 'organisation_id']
    values = [
        ('/accounts/v1/accounts', 200, 1500, '5126b464-a7d0-4cfc-aa53-8feda9d4181b'),
        ('/accounts/v1/accounts', 200, 1000, '5126b464-a7d0-4cfc-aa53-8feda9d4181b'),
        ('/accounts/v1/accounts', 200, 900, '5126b464-a7d0-4cfc-aa53-8feda9d4181b'),
        ]
    

    df = spark.createDataFrame(values, columns)
    df.repartition(1).write\
        .mode('overwrite')\
        .option("header", "true")\
        .csv("s3a://test-bucket/fixture-file")


    def teardown():
        print("teardown")

    request.addfinalizer(teardown)

    return "resource"

def test_glue_job(resource, spark):
    sut.execute(spark, [])

    
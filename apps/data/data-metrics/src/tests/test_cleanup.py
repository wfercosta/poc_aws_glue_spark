from pyspark.sql.session import SparkSession
import pytest
import jobs.cleanup as sut
from moto import mock_s3

@pytest.fixture
def resource(request: pytest.FixtureRequest, spark: SparkSession):

    values = [('k1', 1),('k2', 2)]
    columns = ['key', 'value']

    df = spark.createDataFrame(values, columns)

    df.write.csv("s3://bucket/source.csv")

    def teardown():
        print("teardown")

    request.addfinalizer(teardown)

    return "resource"

def test_cleanup_execute(resource, spark):
    sut.execute(spark, [])

    
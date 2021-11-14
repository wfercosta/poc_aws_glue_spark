import argparse
import jobs
import inspect
import sys
from pkgutil import iter_modules
from importlib import import_module
from pyspark.sql import SparkSession

def has_required_functions(ref):
    """Validates if there are the required functions to configure
    and execute a spark program

    Parameters:
    ref (module): Python module that acts as entrypoint

    Returns:
    bool: True if has the required functions
    
    """
    return (hasattr(ref, 'configure_subparser') \
                and inspect.isfunction(ref.configure_subparser)) \
            and (hasattr(ref, 'execute') \
                and inspect.isfunction(ref.execute)) \

def create_spark_instance(name: str) -> SparkSession:
    """Creates a spark intance to execute the job

    Parameters:
    name (str): The name of spark job

    Returns:
    SparkSession: Returns the spark session that was created
    
    """
    return SparkSession.builder \
                .master("local") \
                .appName(name) \
                .getOrCreate()

def parse_cli_arguments(args: list, modules: dict):
    """Parses the command line arguments

    Parameters:
    args (list): List of the arguments
    modules (dict): List of spark jobs entrypoints

    Returns:
    list: Returns the arguments parsed
    
    """
    parser = argparse.ArgumentParser()
    subparsers = parser.add_subparsers(dest='action')    

    for k in list(modules.keys()):
        if not has_required_functions(modules[k]):
            del modules[k]
            continue

        subparser = subparsers.add_parser(k)
        modules[k].configure_subparser(subparser)

    return parser.parse_args(args) 

if __name__ == '__main__':
    modules = { module.name: import_module('jobs.%s' % module.name) \
        for module in iter_modules(jobs.__path__)}

    arguments = parse_cli_arguments(sys.argv[1:], modules)
    
    if arguments.action not in modules:
        print("Has no action configured")
    else:
        spark = create_spark_instance(arguments.action)
        modules[arguments.action].execute(spark, arguments)

    
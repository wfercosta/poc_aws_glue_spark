def configure_subparser(subparser):
        subparser.add_argument("--var")

def execute(spark, args):
    print("arguments: %s " % args)
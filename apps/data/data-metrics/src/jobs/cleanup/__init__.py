

def configure_subparser(subparser):
    subparser.add_argument("--input")
    subparser.add_argument("--output")


def execute(spark, args):
    print("arguments: %s " % args)
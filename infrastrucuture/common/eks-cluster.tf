
module "eks" {
  source          = "terraform-aws-modules/eks/aws"
  cluster_name    = local.cluster_name
  cluster_version = "1.20"
  subnets         = module.vpc.private_subnets

  vpc_id = module.vpc.vpc_id

  workers_group_defaults = {
    root_volume_type = "gp2"
  }

  

  worker_groups = [
    {
      name                          = "worker-group-1"
      instance_type                 = "t2.small"
      additional_userdata           = "echo foo bar"
      asg_desired_capacity          = 2
      additional_security_group_ids = [aws_security_group.worker_group_mgmt_one.id]
    },
    {
      name                          = "worker-group-2"
      instance_type                 = "t2.medium"
      additional_userdata           = "echo foo bar"
      additional_security_group_ids = [aws_security_group.worker_group_mgmt_two.id]
      asg_desired_capacity          = 1
    },
  ]
}

data "aws_eks_cluster" "cluster" {
  name = module.eks.cluster_id
}

data "aws_eks_cluster_auth" "cluster" {
  name = module.eks.cluster_id
}

resource "aws_iam_policy" "s3_metrics" {
  name="s3_metrics_policy"

  policy = jsonencode({
      Version: "2012-10-17",
      Statement: [
          {
              Effect: "Allow",
              Action: [
                  "s3:*"
              ],
              Resource: [
                  "arn:aws:s3:::wfercosta-metrics/logs/*"
              ]
          },
          {
              Effect: "Allow",
              Action: [
                  "s3:*"
              ],
              Resource: [
                  "arn:aws:s3:::wfercosta-metrics/logs"
              ]
          }
      ]
  })
}

resource "aws_iam_role_policy_attachment" "s3_metrics" {
  role       = module.eks.cluster_iam_role_name
  policy_arn = aws_iam_policy.s3_metrics.arn
}

provider "kubernetes" {
  host                   = data.aws_eks_cluster.cluster.endpoint
  cluster_ca_certificate = base64decode(data.aws_eks_cluster.cluster.certificate_authority.0.data)
  token                  = data.aws_eks_cluster_auth.cluster.token
}




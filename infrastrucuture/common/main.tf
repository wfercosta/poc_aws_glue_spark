variable "region" {
  default     = "us-east-1"
  description = "AWS region"
}

provider "aws" {
  region = var.region
}

terraform {
  backend "s3" {
    bucket         = "wfercosta-terraform-state"
    key            = "terraform.tfstate"
    region         = "us-east-1"

    dynamodb_table = "terraform-state"
    encrypt        = true
  }
}

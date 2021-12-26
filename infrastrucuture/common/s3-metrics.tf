resource "aws_s3_bucket" "s3_metrics" {
  bucket = "wfercosta-metrics"
 
  force_destroy = true

  versioning {
    enabled = true
  }
  
}




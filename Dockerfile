FROM ubuntu:xenial

RUN apt-get update -qq -y \
	&& apt-get install -y \
		wget \
		curl \
		unzip \
		software-properties-common \
		locales \
	&& apt-get clean \
	&& locale-gen en_US.UTF-8 \
	&& rm -rf /var/lib/apt/lists/*

RUN curl "https://s3.amazonaws.com/aws-cli/awscli-bundle.zip" -o "/tmp/awscli-bundle.zip" \
	&& unzip /tmp/awscli-bundle.zip -d /tmp/ \
    && /usr/bin/python3 /tmp/awscli-bundle/install -i /usr/local/aws -b /usr/local/bin/aws

RUN apt-add-repository ppa:ansible/ansible \
    && apt-get update -qq -y \
    && apt-get install -y ansible \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

RUN curl "https://releases.hashicorp.com/terraform/0.11.3/terraform_0.11.3_linux_amd64.zip?_ga=2.252958281.1117305839.1518124363-282396174.1506865120" -o "/tmp/terraform.zip" \
	&& unzip /tmp/terraform.zip -d /bin

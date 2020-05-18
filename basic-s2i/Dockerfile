FROM redhat-openjdk-18/openjdk18-openshift
MAINTAINER Richard Weber <riche.weber@gmail.com>

LABEL io.k8s.description="Java with JDK" \
    io.openshift.tags="builder,java,jdk" \
    io.openshift.s2i.scripts-url="image:///usr/libexec/s2i" \
    io.openshift.s2i.destination="/tmp" \
    org.jboss.deployments-dir="/deployments" \
    com.redhat.deployments-dir="/deployments" \
    com.redhat.dev-mode="JAVA_DEBUG:false" \
    com.redhat.dev-mode.port="JAVA_DEBUG_PORT:5005"

# Temporary switch to root
USER root

#RUN yum -y update \
#  && yum install -y --setopt=skip_missing_names_on_install=False \
#       unzip rsync \
#       java-1.8.0-openjdk \
#       java-1.8.0-openjdk-devel \
#  && yum clean all \

# S2I scripts
COPY s2i /usr/libexec/s2i
ADD README.md /usr/libexec/s2i/usage.txt
RUN chmod +x /usr/libexec/s2i/*

RUN chmod -R 777 /opt

USER 1001

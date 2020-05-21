#FROM redhat-openjdk-18/openjdk18-openshift
FROM openjdk:8-jdk-alpine
MAINTAINER Riddhiman Kar <riddhiman.kar@fisglobal.com>

LABEL io.k8s.description="Java with JDK" \
    io.openshift.tags="builder,java,jdk" \
    io.openshift.s2i.scripts-url="image:///usr/local/s2i" \
    io.openshift.s2i.destination="/tmp" \
    org.jboss.deployments-dir="/deployments" \
    com.redhat.deployments-dir="/deployments" \
    com.redhat.dev-mode="JAVA_DEBUG:false" \
    com.redhat.dev-mode.port="JAVA_DEBUG_PORT:5005"

# Temporary switch to root
USER root

ENV GRADLE_VERSION=6.4 \
    APK_GLIBC_VERSION=2.29-r0 \
	PROTOBUF_VERSION=3.11.4 \
	PROTOC_HOME=/usr/bin/protoc 

#RUN yum -y update \
#  && yum install -y --setopt=skip_missing_names_on_install=False \
#       unzip rsync \
#       java-1.8.0-openjdk \
#       java-1.8.0-openjdk-devel \
#  && yum clean all \

# Install CURL
#RUN apk --no-cache add curl
# Install CURL
#RUN apk --no-cache add curl

RUN set -ex ;\
  	apk add --no-cache \
			gcc \
			g++ \
			curl \
			make \
		    glibc-${APK_GLIBC_VERSION}.apk \
		    ;\
	rm -rf /var/cache/apk/*

# Install Gradle
RUN set -x ; [ -z "$GRADLE_VERSION" ] && { echo "GRADLE_VERSION must be defined" ; exit 1 ; } ; \
    GRADLE_HOME=/opt/gradle/${GRADLE_VERSION} ; \
    mkdir -p ${GRADLE_HOME} \
	&& curl -sSL "https://downloads.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip" \
	-o /tmp/gradle-bin.zip \
	&& unzip -q /tmp/gradle-bin.zip -d $GRADLE_HOME \
	&& mv $GRADLE_HOME/gradle-${GRADLE_VERSION}/* $GRADLE_HOME \
	&& rm -rf $GRADLE_HOME/gradle-${GRADLE_VERSION} \
	&& ln -s $GRADLE_HOME/bin/gradle /usr/bin/gradle \
	&& rm -f /tmp/gradle-${GRADLE_VERSION}-bin.zip
	
###################
# Install PROTOBUF
# Only works for 3.0.0 and up
#
RUN set -ex ;\
	# Install PROTOBUF
	# Only works for 3.0.0 and up
	curl -#sL "https://github.com/google/protobuf/releases/download/v$PROTOBUF_VERSION/protobuf-java-$PROTOBUF_VERSION.tar.gz" | tar -xz -C /usr/local ;\
    cd /usr/local/protobuf-$PROTOBUF_VERSION && ./configure --prefix=/usr ;\
    make && make install ;\
    rm -rf /usr/local/protobuf-$PROTOBUF_VERSION
	
# S2I scripts
COPY s2i /usr/local/s2i
ADD README.md /usr/local/s2i/usage.txt
RUN chmod +x /usr/local/s2i/*

RUN chmod -R 777 /opt

EXPOSE 9001
EXPOSE 9002

USER 1001

CMD [ "/usr/local/s2i/usage" ]

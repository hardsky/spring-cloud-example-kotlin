FROM docker.elastic.co/logstash/logstash:7.8.1
RUN rm -f /usr/share/logstash/pipeline/logstash.conf
COPY logstash.conf /usr/share/logstash/pipeline/logstash.conf
COPY logstash.yml config/logstash.yml

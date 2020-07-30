#!/bin/sh

consul kv put config/application/data @application.properties
consul kv put config/api-ws/data @api-ws.properties
consul kv put config/zuul/data @zuul.properties
consul kv put config/id-ws/data @id-ws.properties
consul kv put config/approver-ws/data @approver-ws.properties
consul kv put config/mailer-ws/data @mailer-ws.properties

package com.colm.app

import com.colm.queueing.RabbitMQConnection

class ConnectController {

  def index() {
  }

  def print() {

    Long numberOfMessagesToSend
    if (((String) params.numberOfMessages).isNumber()) {
      numberOfMessagesToSend = Long.parseLong(params.numberOfMessages)
    }
    if (!numberOfMessagesToSend || numberOfMessagesToSend < 0) {
      numberOfMessagesToSend = 1
    }


    RabbitMQConnection rabbitMQConnection = RabbitMQConnection.instance.setUpConnection(params.username, params.password, params.host, params.virtualHost)
    if (rabbitMQConnection.connection) {
      def channel = rabbitMQConnection.createChannel()
      String queueName = 'someRandomQueue'
      channel.queueDeclare(queueName, false, false, false, null)
      String message = "Hello World!"
      numberOfMessagesToSend.times {
        channel.basicPublish("", queueName, null, message.getBytes())
      }
      println("Sent '" + message + "' $numberOfMessagesToSend time")
    }


    def targetUri = params.targetUri ?: "/"
    redirect(uri: targetUri)
  }


}

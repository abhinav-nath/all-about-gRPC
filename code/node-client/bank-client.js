const grpc = require('grpc')
const protoLoader = require('@grpc/proto-loader');

const packageDef = protoLoader.loadSync('proto/bank-service.proto')
const protoDesc = grpc.loadPackageDefinition(packageDef)

const client = new protoDesc.BankService('localhost:8181', grpc.credentials.createInsecure())

client.getBalance({accountNumber: 6}, (err, balance) => {
    if(err) {
        console.log('Error occurred')
    } else {
        console.log('Received : ' + balance.amount)
    }
})
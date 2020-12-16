const cassandra = require('cassandra-driver');

const client = new cassandra.Client({
    contactPoints: ['localhost'],
    localDataCenter: 'datacenter1',
    credentials: { username: 'cassandra', password: 'cassandra' }
});

const query = `

CREATE KEYSPACE simplex WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 3};


`;

client.execute(query)
    .then(result => console.log(result));
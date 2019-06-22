CREATE KEYSPACE IF NOT EXISTS cloudmessagestream WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}  AND durable_writes = true;
CREATE TABLE IF NOT EXISTS cloudmessagestream.tbl_cloud_message(
	 sender text,
	 receiver text,
	 id timeuuid,
	 message text,
	 PRIMARY KEY (sender,id)
)WITH CLUSTERING ORDER BY (id DESC);
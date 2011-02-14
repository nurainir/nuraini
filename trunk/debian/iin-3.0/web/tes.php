<?php
mysql_connect("localhost", "mama", "nono") or die(mysql_error());

mysql_select_db("iin") or die(mysql_error());

$hasil = mysql_query("SELECT * FROM mytable")
or die(mysql_error());

while ($record = mysql_fetch_array($hasil))
{
echo "No: ".$record['id']. "<br>";
echo "Nama: ".$record['name']. "<br><br>";
}

?>

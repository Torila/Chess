<?php
    $servername = 'localhost';
    $username = 'vnlaughlin';
    $password = 'cs480';
    $database = 'vnlaughlin';
    $conn = new mysqli($servername, $username, $password, $database);
    $_SESSION['v1'] = $conn;
?>

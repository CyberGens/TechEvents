<?php
$password=$_GET["password"];
$hash=$_GET["hash"];
/*$options = [
  'cost' => 13
];
echo password_hash('123', PASSWORD_BCRYPT, $options);
*/
if (password_verify($password, $hash)) {
    echo 'true';
} else {
    echo 'false';
}

?>

<!DOCTYPE html>
<html>
<head>
    <title>test</title>
</head>
<body>
用户ID：${userInfo.id}<br>
用户姓名：${userInfo.name}<br>
用户性别：<#if userInfo.gender = 1>男<#elseif userInfo.gender = 2>女<#else>未知</#if><br>
用户身份证号：${userInfo.identityCardNumber}<br>
用户年龄：${userInfo.age}<br>
<#list integers as int>
${int}<br>
</#list>
</body>
</html>
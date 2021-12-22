<html>
<head>
</head>
<body>
  <h1>Name: ${name}</h1>
  <h1>Créditos: ${credits}</h1>
  <#list projects as project>
    <li>id: ${project.id} - name: ${project.name} </li>
  </#list>
</body>
</html>
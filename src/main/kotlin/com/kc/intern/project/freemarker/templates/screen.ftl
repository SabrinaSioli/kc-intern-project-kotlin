<html>
<head>
    <title>${name}</title>
</head>
<body style="background: ${backgroundColor.hex}; left:${location.x}; top:${location.y}">
    <#if components??>
        <#list components as component>
            <#if component.type == "Button">
            <button style="width: ${component.size.width}; height: ${component.size.height}; left:${component.location.x}; top:${component.location.y}; background: ${component.color.hex}; font-family: ${component.font.family}; font-size: ${component.font.size}">${component.label}</button>
            </#if>
        </#list>
    </#if>
</body>
</html>
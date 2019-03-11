<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
    <title>H W</title>
    <script language="javascript">
    function _onchange() {
        var sel = document.getElementById("gstype");
        if (sel.value == "java") {
            document.getElementById("d1").style.value = "none";
            document.getElementById("d2").style.value = "";
        } else if (sel.value == "net") {
            document.getElementById("d1").style.value = "";
            document.getElementById("d2").style.value = "";
        } else {
            document.getElementById("d1").style.value = "none";
            document.getElementById("d2").style.value = "none";
        }
    }
    </script>
</head>
<body>
<select id="gstype" onChange="_onchange()"> 
<option value="">--</option> 
<option value="java">java</option> 
<option value="net">net</option> 
</select> 
<option id="d1" style="display:none;">d1</option> 
<option id="d2" style="display:none;">d2</option> 
</body>
</html>
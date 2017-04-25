<%@ page language="java" import="java.util.*,com.ruiyi.wechat.string.Language,com.ruiyi.wechat.model.CarInfo" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% 
	List<CarInfo> carList=(List<CarInfo>) request.getAttribute("carlist");
%>


<html>
<head>
 <script type="text/javascript" src="../jsp/js/head.js"></script>
</head>
<body>



<div data-role="page">
  <div data-role="header">
  <h1>设备服务购买</h1>
  </div>

  <div data-role="content" >
  
  <div id="resultDiv">
  </div>
  
  
      <label >余额:100</label>
  
  <form>
<div class="ui-field-contain">
    <label for="select-native-1">您的车辆:</label>
			<select name="did" id="did"  data-corners="false" >
				

		<%for(int i=0;i<carList.size();i++){%>
			
					<option value="<%= carList.get(i).getId() %>">
						<%=carList.get(i).getAlias()%>
					</option>
			
			<%}%>
				</select>
</div>
</form>
  
  
    <form>
      
        <fieldset data-role="controlgroup" >
      <legend>平台服务当前选的服务：未选择</legend>
        <label for="pt120">120元一年</label>
        <input type="radio" name="pt" id="pt120" value="120">
        <label for="pt200">200元两年</label>
     <input type="radio" name="pt" id="pt200" value="200">
        <label for="pt260">260元三年</label>
        <input type="radio" name="pt" id="pt260" value="260">
      </fieldset>

      <fieldset data-role="controlgroup" >
      <legend>流量服务当前选的服务：未选择</legend>
         <label for="ll15">15元三个月</label>
        <input type="radio" name="ll" id="ll15" value="15">
    <label for="ll60">60元一年</label>
        <input type="radio" name="ll" id="ll60" value="60">
      </fieldset>

      <fieldset data-role="controlgroup" >
      <legend>短信服务当前选的服务：未选择</legend>
          <label for="dx10">10元100条</label>
             <input type="radio" name="dx" id="dx10" value="10">
      </fieldset>
      
      <center>
      <input type="button" data-inline="true" value="提交" onclick="getval()">
           <input type="reset" data-inline="true" value="重置">
           </center>
    </form>
  </div>
</div>

</body>


<script language="javascript">


	function getval(){
	
	var pt=$('input:radio[name="pt"]:checked').val();
 	var ll=$('input:radio[name="ll"]:checked').val();
 	var dx=$('input:radio[name="dx"]:checked').val();
 	
 	
 	if(pt!=null)
 		alert("pt="+pt+"---");
 	if(ll!=null)
 	 	alert("ll="+ll+"---");
 	if(dx!=null)
 		alert("dx="+dx+"---");
	
	var url="TotalServlet?action=buyservers&weid="+<%=request.getAttribute("weid")%>+"&pt="+pt+"&ll="+ll+"&dx="+dx;
	
	alert(url);
	
	 var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} 
		 		xmlhttp.onreadystatechange = function() {
		 if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			 
			  resultDiv.innerHTML="成功！"
		 }else
		 resultDiv.innerHTML="登錄中.."
		 }; 
	 	xmlhttp.open("GET",url, true);
		xmlhttp.send(); 
	
	
	
	}

 	


	

</script>
</html>



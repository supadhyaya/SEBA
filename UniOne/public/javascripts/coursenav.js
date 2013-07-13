
<!--
function hide_menu(node_id,menu_type){

var node_prefix="";

switch(menu_type){

case 1:{

node_prefix="node_";

}break;

case 2:{

node_prefix="main_node_";

}break;

}

var node=document.getElementById(node_prefix+node_id);

var node_status=document.getElementById(node_prefix+node_id+"_status");

var node_text=document.getElementById(node_prefix+node_id+"_title");
if(node_status.value=="1"){

node.style.display = "none"; 

}else{

node.style.display = ""; 

}

if(node_status.value=="1"){

node_status.value="0";

node_text.innerHTML="&#9658";

}else{

node_status.value="1";

node_text.innerHTML="&#9660";

}

return false;

}
-->

document.write("<script language=javascript src='//tajs.591.com.tw/fl.js'></script>");
if(typeof(oxads) == "undefined"){//避免重複引用
    var oxads = [];
    var indexHyAd = [43,44,45,46,47,48];
    indexTxtArr = [];
    function in_array(search,array){
    for(var i in array){
        if(array[i]==search){
            return true;
        }
    }
    return false;
}
function AD_SHOW(zoneid, pageid) {
    var oxad = {};
    oxad.zid = zoneid;
    oxad.pid = pageid;
    oxads.push(oxad);
}

function buildAd() {
    var ox_spc = "",
        ox_source = "",
        ox_p = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'tajs.591.com.tw/spc3.php',
        ox_r = Math.floor(Math.random() * 99999999),
        ox_zone = [],
        ox_chart = document.charset;

    for (var i in oxads) {
        ox_zone.push(oxads[i]['zid'])
    }
    ox_zone = ox_zone.join('|');
    var OA_spc="";
    OA_spc+=ox_p+"?zones="+ox_zone;
    OA_spc+="&amp;source="+(ox_source)+"&amp;r="+ox_r;
    OA_spc+=(document.charset ? '&amp;charset='+document.charset : (document.characterSet ? '&amp;charset='+document.characterSet : ''));
    if (window.location) OA_spc+="&amp;loc="+(window.location);
    if (document.referrer) OA_spc+="&amp;referer="+(document.referrer);
    OA_spc+="&callback=fillData";
    // ox_spc += ox_p + "?zones=" + ox_zone + "&source=" + ox_source + "&r=" + ox_r + "&charset=" + ox_chart+"&callback=fillData";
    
    getScript(OA_spc);
}
function addHtml(id,data){
    var divId = document.getElementById("PAGE_AD_" + id);
    if (divId) {
        if (data["contenttype"] == 'swf') {
            var randoms = Math.floor(Math.random() * 99999999);
            var aLink = document.createElement("div");
            aLink.setAttribute("id","ox_"+randoms+"");
            aLink.style.display = "inline";
            aLink.innerHTML ="<div id='ox_"+randoms+"' style='display: inline;'><a href='' target='_blank'><img src='"+data["flashCover"]+"' width='"+data["width"]+"' height='"+data["height"]+"' alt='"+data["alt"]+"' border='0' /></a></div>";
            var _html = '<a href="' + data["clickUrl"] + '" target="_blank" title="' + data["alt"] + '" style="display:block; width:' + data["width"] + 'px; height:' + data["height"] + 'px; position:absolute; top:0; left:0; filter:alpha(opacity=0); opacity:0;z-index:1000"><img src="//images.591.com.tw/index/design/btn_cartoon.png" width="'+data["width"]+'" height="'+data["height"]+'"></a>';
            divId.innerHTML = _html;
            divId.style.position = "relative";
            divId.style.zIndex = "1";
            divId.insertBefore(aLink,null);
            var ox_swf = new FlashObject(data['bannerContent'], id, data['width'], data['height'], data['pluginVersion']);
            for (var k in data.swfParams) {
                ox_swf.addVariable(k, data.swfParams[k]);
            }
            if (data.transparent) {
                ox_swf.addParam('wmode', 'transparent')
            } else {
                ox_swf.addParam('wmode', 'opaque');
            };
            ox_swf.addParam('allowScriptAccess', 'always');
            ox_swf.write('ox_' + randoms);
        }else if(data["contenttype"] == 'txt'){
            divId.innerHTML = "";
            if (data != "undefined") {
                var _html = '<a href="'+data['clickUrl']+'" target="_blank"  title="' + data["bannerContent"] + '">'+data['bannerContent']+'</a>';
            }
            divId.innerHTML = _html;
        }else if(data["contenttype"] == ''){
            divId.innerHTML = "";
            if (data != "undefined") {
                var _html = data['bannerContent'];
            }
            divId.innerHTML = _html;
        }else{
            divId.innerHTML = "";
            if (data != "undefined") {
                var imgUrl = data['bannerContent'];
                var _html = data['prepend']+'<a href="'+data['clickUrl']+'" target="_blank"  title="' + data["alt"] + '" style="display:block;"><img src="'+imgUrl+'" width="'+data['width']+'" height="'+data['height']+'" alt="' + data["alt"] + '" title="" border="0"></a>'+data['append'];
            }
            divId.innerHTML = _html;
        }
        if(document.getElementById("PAGE_ALT_" + id)){//新房屋首頁
            document.getElementById("PAGE_ALT_" + id).innerHTML = data["alt"];
        }
        //曝光率統計
        var beaUrl = data['beaconUrl'];
        if (window.location) beaUrl+="&amp;loc="+(window.location);
        if (document.referrer) beaUrl+="&amp;referer="+(document.referrer);
        beaUrl += data['beaconId'].split('beacon_')[1];
        var tjhtml = document.createElement("div");
        tjhtml.setAttribute("id",data['beaconId']);
        tjhtml.style.position = "absolute";
        tjhtml.style.left = "0px";
        tjhtml.style.top = "0px";
        tjhtml.style.visibility = "hidden";
        tjhtml.innerHTML = '<img src="'+beaUrl+'" width="0" height="0" alt="" style="width: 0px; height: 0px;">';
        divId.insertBefore(tjhtml,null);
    }
}
function addHousingAd(id,data,boxId){//新房屋flash文字旁邊的廣告
    var divId = document.getElementById(boxId);
    if(divId){
        if (data["contenttype"] == 'swf') {
            divId.innerHTML = data['alt'];
        }else if(data["contenttype"] == 'jpeg' || data["contenttype"] == 'gif' ){
            divId.innerHTML = '<a href="'+data['clickUrl']+'" target="_blank"  title="' + data["alt"] + '"><img src="'+data['bannerContent']+'" width="'+data['width']+'" height="'+data['height']+'" alt="' + data["alt"] + '" title="" border="0"></a>';
            if(in_array(id,indexHyAd)){//首頁的海悅廣告特殊處理，添加
                if(data['alt']){
                    indexTxtArr.push(data['alt']);
                }
            }
        }
         //曝光率統計
        var beaUrl = data['beaconUrl'];
        if (window.location) beaUrl+="&amp;loc="+(window.location);
        if (document.referrer) beaUrl+="&amp;referer="+(document.referrer);
        beaUrl += data['beaconId'].split('beacon_')[1];
        var tjhtml = document.createElement("div");
        tjhtml.setAttribute("id",data['beaconId']);
        tjhtml.style.position = "absolute";
        tjhtml.style.left = "0px";
        tjhtml.style.top = "0px";
        tjhtml.style.visibility = "hidden";
        tjhtml.innerHTML = '<img src="'+beaUrl+'" width="0" height="0" alt="" style="width: 0px; height: 0px;">';
        divId.insertBefore(tjhtml,null);
    }
}
function fillData(OA_output) {
    if (!OA_output) return false;
    var count = 0;//計數
    //因為新房屋首頁廣告不一致，需要區分
    for (key in OA_output) {　
        if(OA_output[key]){
            if(oxads[count]['zid'] == oxads[count]['pid'].split('PAGE_AD_')[1]){
                addHtml(key,OA_output[key]);
            }else{
                addHousingAd(oxads[count]['zid'],OA_output[oxads[count]['zid']],oxads[count]['pid']);
            }
        }
        count ++;
    }　　
}
function getScript(url, callback) {
    var dom = document.createElement('script');
    dom.src = url;
    dom.onload = dom.onreadystatechange = function() {
        if (!this.readyState || 'loaded' === this.readyState || 'complete' === this.readyState) {
            this.onload = this.onreadystatechange = null;
            this.parentNode.removeChild(this);
        }
        callback && callback();
        //this.parentNode.removeChild(this);
        if(indexTxtArr.length>0){
            photoPlay();
        }
    };
    document.getElementsByTagName('head')[0].appendChild(dom);
}
//高級瀏覽器等同$(function(){})ie9+
if (document.addEventListener) {
    document.addEventListener("DOMContentLoaded", function() {
        document.removeEventListener('DOMContentLoaded', arguments.callee, false);
        buildAd();
    }, false);
} else if (document.attachEvent) { // ie
    document.attachEvent("onreadystatechange", function() {
        if (document.readyState === "complete") {
            document.detachEvent("onreadystatechange", arguments.callee);
            buildAd();
        }
    });
}
//首頁海悅廣告
function photoPlay(){ 
  var sWidth = 600; //获取焦点图的宽度（显示面积）
  var len = $("#myFocus ul li").length; //获取焦点图个数
  var index = 0; 
  var picTimer;
  var txtArr = indexTxtArr;
  
  //以下代码添加数字按钮和按钮后的半透明条，还有上一页、下一页两个按钮
  var btn = "<div class='btn'>";
  for(var i = 0; i < len; i++) {
    btn += "<span>"+ indexTxtArr[i] +"</span>";
  }
  btn += "</div>";
  $("#myFocus").append(btn);
  //为小按钮添加鼠标滑入事件，以显示相应的内容
  $("#myFocus .btn span").live('click',function(){
    index = $("#myFocus .btn span").index(this);
    showPics(index);
  })
  $("#myFocus .btn span").eq(0).trigger("click");
  //本例为左右滚动，即所有li元素都是在同一排向左浮动，所以这里需要计算出外围ul元素的宽度
  $("#myFocus ul").css("width",sWidth*len);
  //鼠标滑上焦点图时停止自动播放，滑出时开始自动播放
  $("#myFocus").hover(function() {
    clearInterval(picTimer);
  },function() {
    picTimer = setInterval(function() {
      showPics(index);
      index++;
      if(index == len) {index = 0;}
    },4000); //此3000代表自动播放的间隔，单位：毫秒
  }).trigger("mouseleave");
  
  //显示图片函数，根据接收的index值显示相应的内容
  function showPics(index) { //普通切换
    var nowLeft = -index*sWidth; //根据index值计算ul元素的left值
    $("#myFocus ul").stop(true,false).animate({"left":nowLeft},300); //通过animate()调整ul元素滚动到计算出的position
    $("#myFocus .btn span").removeClass("on").eq(index).addClass("on"); //为当前的按钮切换到选中的效果
    $("#myFocus .btn span").stop(true,false).animate({"opacity":"1.0"},300).eq(index).stop(true,false); //为当前的按钮切换到选中的效果
  }   
}

}

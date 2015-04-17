/**
 * Created by Hugh on 2015/4/16 0016.
 */

function javascript_print(xml) {
    /*
     *  以下代码创建相关DOM节点，包括table，tbody，和表头
     * */
    var table = document.createElement("table");
    var tbody = document.createElement("tbody");
    var table_title = document.createElement("tr");
    var table_title_str = new Array("NO.","Name","Age","Gender","Income","Education","Business","Email Address","Phone","Address");
    var title_index;
    for (title_index in table_title_str) {
        var th_unit = document.createElement("th");
        var th_text = document.createTextNode(table_title_str[title_index]);
        th_unit.appendChild(th_text);
        table_title.appendChild(th_unit);
    }
    tbody.appendChild(table_title);
    /*
    *   以下代码首先利用xpath查询所有person节点，遍历每个person节点
    *   对于子孙interest节点大于5个的，调用函数，得到一列（tr）
    *   将该tr加到tbody中，最后将tbody加到table中
    * */
    //var xpath1 = "//profile[@income>100000]";
    var xpath = "//person";
    var result = xml.documentElement.select_nodes(xpath);
    var count = 0;

    //var str = "";
    var index;
    for (index in result){
        var person = result[index];
        var interests = person.select_nodes("./profile/interest");
        //str += " "+interests.length;
        if (interests.length > 5) {
            var raw = js_get_raw(result[index]);
            var number = document.createElement("td");
            var number_text = document.createTextNode("" + (++count));
            number.appendChild(number_text);
            raw.insertBefore(number,raw.childNodes[0]);
            if (count % 2 == 0) {
                raw.className = "even";
            } else {
                raw.className = "odd";
            }
            tbody.appendChild(raw);
            //alert(raw.innerHTML);
            //count++;
        }
    }
    table.appendChild(tbody);
    document.getElementsByClassName("content")[0].appendChild(table);
    //alert(str);
    //alert(count);
}

function js_get_raw(right_person) {
    var raw = document.createElement("tr");
    var person_attr = new Array("name","age","gender","income","education","business","emailaddress","phone","address");

    var index;
    for (index in person_attr) {
        var unit = document.createElement("td");

        var xpath1 = "./"+ person_attr[index];                  //一级子节点
        var child_node = right_person.select_nodes(xpath1)[0];

        if (child_node==null){                                  //profile下子节点
            var xpath2 = "./profile/"+ person_attr[index];
            child_node = right_person.select_nodes(xpath2)[0];
        }
        if (child_node!=null){
            var attr_info = child_node.getValue();
        }
        if (person_attr[index] == "income") {                   //profile的属性
            var xpath3 = "./profile";
            child_node = right_person.select_nodes(xpath3)[0];
            var attr_info = child_node.getAttribute(person_attr[index]);
        }
        //alert(attr_info);
//alert(index +" " +attr_info);
        //var attr_info = right_person.children(person_attr[index]).text();
//alert(attr_info);
        var text = document.createTextNode(child_node == null? "/" : attr_info);
        unit.appendChild(text);
        raw.appendChild(unit);
    }
//    var a = right_person.select_nodes("./name")[0].childNodes[0].nodeValue;
//    alert(a);

    return raw;
}
/*
*   返回节点内容(判断、遍历、拼接字符串)
* */
Element.prototype.getValue = function (){
    var nodes = this.childNodes;
    //alert(nodes.length);
    if (nodes.length == 1) {
        return nodes[0].nodeValue;
    } else {
        var value = "";
        for (var i=0; i<nodes.length; i++){
            if (nodes[i].nodeType != Node.TEXT_NODE)
                value += nodes[i].getValue() + " ";
        }
        return value;
    }
}
/*
*   根据xpath表达式返回nodes节点数组 Array<Element>
* */
Element.prototype.select_nodes = function (xpath){
    var ua = navigator.userAgent.toLowerCase();
    var rMsie = /(msie\s|trident.*rv:)([\w.]+)/;
    var match = rMsie.exec(ua);
    if (match!=null){
        alert("不支持IE，谢谢合作0.0");
    }else if(window.XPathEvaluator){        //Chrome,Firefox,...
        var xpathObj=new XPathEvaluator();

        if(xpathObj){
            var result=xpathObj.evaluate(xpath,this,null,XPathResult.ORDERED_NODE_ITEARTOR_TYPE,null);
            var nodes=new Array();
            var node;
            while((node = result.iterateNext())!=null) {
                nodes.push(node);
            }
            return nodes;
        }else{
            return null;
        }
    }else{
        return null;
    }
}

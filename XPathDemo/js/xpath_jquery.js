/**
 * Created by Hugh on 2015/4/15 0015.
 */
/*
*   并不能算实际意义上的xpath查询，应该是对javascript中的实现进行了封装，并不能直接使用XPath语句查询
* */
function jquery_print(xml) {
    $(".content").print_info(xml);
}

$.fn.print_info = function (xml) {
    var count = 0;
    var table = $("<table></table>");
    var table_title = $("<tr>" +
    "<th>NO.</th>" +
    "<th>Name</th>" +
    "<th>Age</th>" +
    "<th>Gender</th>" +
    "<th>Income</th>" +
    "<th>Education</th>" +
    "<th>Business</th>" +
    "<th>Email Address</th>" +
    "<th>Phone</th>" +
    "<th>Address</th>" +
    "</tr>");

    table.append(table_title);

    var path1 = "person";
    var path2 = "interest";

    $(xml).find(path1).each(function() {
        if ($(this).find(path2).length > 5){
            var right_person = $(this);
            var raw = get_raw(right_person);
            raw.prepend("<td>"+ (++count) + "</td>");
            $(table).append(raw);
            if (count%2==0){
                $(raw).addClass("even");
            } else {
                $(raw).addClass("odd");
            }
        }
    });
    $(this).append(table);
}

function get_raw(xml_person){
    var raw = $("<tr></tr>");
    var person_attr = new Array("name","age","gender","income","education","business","emailaddress","phone","address");

    var index;
    for (index in person_attr) {
        var unit = $("<td></td>");
        var attr_info = xml_person.find(person_attr[index]).text();
        if (person_attr[index] == "income") {
            attr_info = xml_person.find("profile").attr("income");
        }
        $(unit).append(attr_info == "" ? "/" : attr_info);
        raw.append(unit);
    }

    return raw;
}

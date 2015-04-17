/**
 * Created by Hugh on 2015/4/16 0016.
 */
function load_xml_ajax(callback){
    $.ajax({
        url: './xml/data.xml',
        type: 'GET',
        dataType: 'xml',
        timeout: 1000,      //设定超时
        cache: false,      //禁用缓存
        error: function() {
            alert("加载xml文档失败,请重试");
        },
        success: function(xml) {
            if (callback){
                callback(xml);
            }
        }
    })
}


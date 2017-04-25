
String.prototype.trim = function () {
    // 用正则表达式将前后空格  
    // 用空字符串替代。  
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
function isArray(obj) {
    return Object.prototype.toString.call(obj) === '[object Array]';
}
$id = document.getElementById;
jQuery.ajaxSetup({
    cache: false //关闭AJAX相应的缓存
});
// 实现类似C#中的Contains()
Array.prototype.contains = function ($value, prop) {
    for (var $i = 0; $i < this.length; $i++) {
        var $element = this[$i];
        if (prop)
            $element = $element[prop]
        if ($element == $value) return true;
    }
    return false;
}



Array.prototype.remove = function ($value) {
    for (var $i = 0; $i < this.length; $i++) {
        var $element = this[$i];
        if ($element == $value) {
            return this.splice($i, 1);

        }
    }
    return this;

}

//jQuery(document).ajaxError(function (e, xhr, settings, exception) {
//    jQuery('body').append('error in: ' + settings.url + ' \n' + 'error:\n' + xhr.responseText);
//});
preventDefault = function (event) {
    if (event == null) {
        event = window.event;
    }
    if (event.stopPropagation) {
        event.stopPropagation();
    }
    if (event.preventDefault) {
        event.preventDefault();
    }

    event.returnValue = false;
    return false;
};

function datefmt(date) {
    return date.split(' ')[0]
}

function postJSON(url, data, callback) {
    if (jQuery.isFunction(data)) {
        loadtext = callback;
        callback = data;
        data = null;
    }

    data = data || {};
    if (isArray(data))
        data = JSON.stringify(data);
    callback = callback || function () { };
    jQuery.post(url, data, function (json) {
        if (json.Errors && json.Errors.length > 0) { 
            var errordiv = jQuery('.validation-summary-valid');
            if (!errordiv[0])
                errordiv = jQuery('.validation-summary-errors');
            errordiv.addClass('validation-summary-errors').removeClass('.validation-summary-valid');
            if (!errordiv[0])
                return jQuery.jBox.info(json.Errors.join('<br/>'), '提示');
            errorul = jQuery("ul", errordiv);
            errorul.html('');
            jQuery.each(json.Errors, function () {
                errorul.append("<li>" + this + "</li>");
            });
            return;
        }
        callback(json.Data || json);
    }, 'json');
}
function getJSON(url, data, callback, loadtext, successtext) {
    if (jQuery.isFunction(data)) {
        callback = data;
        data = null;
    }
    loadtext = loadtext || '加载中...';
    data = data || {};
    if (jQuery.jBox)
        jQuery.jBox.tip(loadtext, 'loading');
    callback = callback || function () { };
    jQuery.getJSON(url, data, function (json) {
        if (jQuery.jBox)
            jQuery.jBox.closeTip();
        callback(json.Data || json);


    });
}

function loading(url, data, callback, loadtext, successtext) {
    if (jQuery.isFunction(data)) {
        callback = data;
        data = null;
    }
    loadtext = loadtext || '加载中...';
    data = data || {};
    jQuery.jBox.tip(loadtext, 'loading');
    jQuery.getJSON(url, data, function (json) {
        if (callback)
            callback(json);
        if (successtext) {
            window.setTimeout(function () { jQuery.jBox.tip(successtext, 'success'); }, 1000);
        }
        else {
            jQuery.jBox.closeTip();
        }
    });
}


function $editor(jqobj, ops) {

    var option = { tools: 'mini',
        upLinkUrl: "/Upload/Attach/",
        upLinkExt: "zip,rar,txt,docx,doc,xlsx,xls",
        //     upLinkUrl: "/Js/editor/upload.aspx",
        //        upLinkExt: "zip,rar,txt", 
        upImgUrl: "/Upload/Image/",
        upImgExt: "jpg,jpeg,gif,png"
    };
    jQuery.extend(option, ops);
    return jqobj.xheditor(option);
}

/*****模式对话框****/
(function ($) {
    $.fn.extend({ mymodal: function (options) {
        var defaults = { top: 100, overlay: 0.8, closeButton: null };
        var me = $(this);
        var overlay = $("#modallayer");
        if (!overlay[0]) {
            overlay = $("<div id='modallayer'></div>");
            $("body").append(overlay);
        }

        options = $.extend(defaults, options);
        var o = options;
        var modal_id = $(this).attr("id");
        if (!o.closeButton)
            $("#lean_overlay").click(close_modal);
        $(o.closeButton).click(close_modal);
        var modal_height = me.outerHeight();
        var modal_width = me.outerWidth();
        overlay.css({ "display": "block", opacity: 0 });
        overlay.fadeTo(200, o.overlay);
        me.css({ "position": "fixed", "opacity": 0, "z-index": 11000, "left": 50 + "%", "margin-left": -(modal_width / 2) + "px", "top": o.top + "px" });
        me.show().fadeTo(200, 1);
        me.on('hide', close_modal)
        function close_modal() {
            overlay.fadeOut(200);
            me.hide();
        }
        return me;
    }
    })
})(jQuery);



loadScript = function (xyUrl, callback) {
    var head, script;
    head = document.getElementsByTagName('head')[0];
    script = document.createElement('script');
    script.type = 'text/javascript';
    script.src = xyUrl;
    script.onload = script.onreadystatechange = function () {
        if (!this.readyState || this.readyState === "loaded" || this.readyState === "complete") {
            callback && callback();
            script.onload = script.onreadystatechange = null;
            if (head && script.parentNode) {
                return head.removeChild(script);
            }
        }
    };
    return head.insertBefore(script, head.firstChild);
};
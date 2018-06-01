function error(msg) {
	layer.alert(msg, {icon: 2});
}

function ok(msg){
	if (!!!msg) {
		msg = "操作成功";
	}
	parent.layer.msg(msg,{icon:1, time: 1500, skin: 'layui-layer-molv'});
}

function okalert(msg) {
	layer.alert(msg, {icon: 1});
}
/**
 * @url url
 * @data 参数，json对象
 * @success 成功回调函数
 */
k.layer.add = function(url, successHandler, config) {
	if (!config) {
		config = {};
	}
	layer.open({
		title: config.title || '新增',
	    type: 2,
	    skin: 'layui-layer-rim', //加上边框
	    area: [layer_default_width, layer_default_height], //宽高
	    content: url,
	    btn: ['确定', '取消'],
	    yes: function(index, layero){ //或者使用btn1
	    	var detailForm = layer.getChildFrame('form', index);
	    	console.log(detailForm);
	    	detailForm.ajaxSubmit({
	    		success: function(data) {
	    			if (successHandler) {
	    				successHandler(data)
	    			} else {
	    				if (isSuccess(data)) {
		    				layer.close(index)
		    				reload();
		    			} else {
		    			}
	    			}
	    		}
	    	});
	    }, cancel: function(index){ //或者使用btn2
	        //按钮【按钮二】的回调
	    },
	    end: function() {
	    	// alert('end')
	    }
	}); 
}

k.layer.edit = function(url, successHandler, config) { 
	if (!config) {
		config = {};
	}
	layer.open({
		title: config.title || '详情',
	    type: 2,
	    skin: 'layui-layer-rim', //加上边框
	    area: [layer_default_width, layer_default_height], //宽高
	    content: url,
	    btn: ['确定', '取消'],
	    yes: function(index, layero){ //或者使用btn1
	    	var detailForm = layer.getChildFrame('form', index);
	    	console.log(detailForm);
	    	detailForm.ajaxSubmit({
	    		success: function(data) {
	    			if (successHandler) {
	    				successHandler(data)
	    			} else {
	    				if (isSuccess(data)) {
		    				layer.close(index)
		    				reload();
		    			} else {
		    			}
	    			}
	    		}
	    	});
	    }, cancel: function(index){ //或者使用btn2
	        //按钮【按钮二】的回调
	    },
	    end: function() {
	    	// alert('end')
	    }
	}); 
};
k.layer.del = function(url, config) {
    if (!config) {
        config = {};
    }
    var msg = '<span style="color: red">{0}</span>'.format(config.msg || '您确定要删除选中的记录吗？');
    layer.confirm(msg, {
        icon: 3,
        title: '警告'
    }, function (index) {
        $.ajax({
            url: url,
            data: {
                ids: config.ids.join(',')
            },
            success: function (data) {
                if (isSuccess(data)) {
                    layer.close(index);
                    reload();
                }
            }
        });
    });
};
layer.config({
	//zIndex:1
});

/** 默认参数 **/
var layer_default_width = '840px';
var layer_default_height = '480px';

var layer_big_width = '1024px';
var layer_big_height = '600px';

var layer_bring_width = '840px';
var layer_bring_height = '400px';

function okAlert(msg) {
	parent.layer.alert(msg, {
		icon: 1,
       skin: 'layui-layer-molv'
	});
}

function errorAlert(msg) {
	parent.layer.alert(msg, {
		icon: 2,
       skin: 'layui-layer-molv'
	});
}

function alert2(msg) {
	parent.layer.msg(msg, {
		icon: 2,
       skin: 'layui-layer-molv',
       time: 1500
	});
}
//function error2(msg) {
//	parent.layer.alert(msg, {
//       skin: 'layui-layer-molv'
//	});
//}
//
//
//function error(msg){
//	parent.layer.msg(msg,{icon: 2, time: 1500, skin: 'layui-layer-molv'});
//}

function ok(msg){
	if (!!!msg) {
		msg = "操作成功";
	}
	parent.layer.msg(msg,{icon:1, time: 1500, skin: 'layui-layer-molv'});
}


function query_submit() {
	$('#query-form').submit();
}

/**
 * 重新打开当前tab页
 */
function reOpen() {
	// $('.J_menuTab.active').trigger('dblclick');
	parent.parentReOpen();
}

function parentReOpen() {
	$('.J_menuTab.active').trigger('dblclick');
}

/**
 * 点击分页
 * @param page 页数
 */
function page_submit(page) {
	var input = '<input type="hidden" name="page" id="page" value="' + page + '"/>';
	var form = $('#query-form');
	form.append(input);
	// $('#page').val(page);
	$('#query-form').submit();
}

function close_layer(index) {
	layer.close();
}

function isSuccess(data) {
	if (Object.prototype.toString.call(data) === "[object String]") {
		data = eval("(" + data + ")");
	}
	if (data.statusCode == 200) {
		ok(data.message);
		return true;
	} else {
		errorAlert(data.message);
		return false;
	}
}

/**
 * 
 */
function paging(ulId, totalPages, page) {
	
	var ul = $('#' + ulId);
	ul.html('');
	if (totalPages == 0) {
		return;
	}
	
	// 上一页
	var li_prev;
	if (page == 1) { // 没有上一页
		li_prev = '<li class="paginate_button previous disabled" aria-controls="DataTables_Table_0" tabindex="0" id="DataTables_Table_0_previous"><a href="#">上一页</a></li>'; 
	} else {
		li_prev = '<li class="paginate_button previous" aria-controls="DataTables_Table_0" tabindex="0" id="DataTables_Table_0_previous"><a href="#" onclick="page_submit(' + (page - 1) + ')">上一页</a></li>';
	}
	ul.append(li_prev);
	
	// 中间只显示6页
	var p_start = 1;
	var p_end = 5;
	if (totalPages <= 5) {
		p_start = 1;
		p_end = totalPages;
	} else {
		p_start = page - 2;
		if (p_start < 1) {
			p_start = 1;
		}
		p_end = p_start + 4;
		if (p_end > totalPages) {
			p_end = totalPages;
		}
	}
	
	for (var i = p_start; i <= p_end; i++) {
		var li = '<li class="paginate_button ' + (i == page ? 'active' : '') + '" aria-controls="DataTables_Table_0" tabindex="0"><a href="javascript:void(0);" onclick="page_submit(' + i + ')">' + i + '</a></li>';
		ul.append(li)
	}
		
	
	// 下一页
	var li_next;
	if (page == totalPages) { // 没有下一页
		li_next = '<li class="paginate_button next disabled" aria-controls="DataTables_Table_0"	tabindex="0" id="DataTables_Table_0_next"> <a href="javascript:void(0);">下一页</a></li>';
	} else {
		li_next = '<li class="paginate_button next" aria-controls="DataTables_Table_0"	tabindex="0" id="DataTables_Table_0_next"> <a href="javascript:void(0);" onclick="page_submit(' + (page + 1) + ')">下一页</a></li>';
	}
	ul.append(li_next);
	
}

/**
 * 查找带回，
 * options的参数：
 * url: 弹窗的地址
 * name: 要带回的属性名称（这个名称只是给人看的，不是提交到后台的，提交到后台的默认就是带回来的id)。对应到的目标页面的table的head的data-field字段
 * idInput: 带回来的id要放到哪个input的value里。idInput是目标input的id
 * nameInput: 带回来的name要显示在哪个input里。 nameInput是目标input的id
 * 
 * @param options
 */
function bring(options) {
	var url = options.url;
	var name = options.name;
	var idInput = options.idInput;
	var nameInput = options.nameInput;
	parent.layer.open({
	    type: 2,
	    skin: 'layui-layer-rim', //加上边框
	    area: [layer_default_width, layer_default_height], //宽高
	    content: url,
	    success: function(layero, index) {
	    	// 隐藏增删除查改等内容
	    	var operation = parent.layer.getChildFrame('#operation', index);
	    	$(operation).hide();
	    },
	    btn: ['选择', '取消'],
	    yes: function(index, layero){ //或者使用btn1
	    	var trs = parent.layer.getChildFrame('#table>tbody>tr.selected>td.bs-checkbox>input[type=radio]:checked', index);
	    	if (trs.length == 0) {
	    		if (idInput) {
		    		$('#' + idInput).val('');
		    	}
		    	if (nameInput) {
		    		$('#' + nameInput).val('');
		    	}
		    	parent.layer.close(index);
		    	return;
	    	} else if (trs.length > 1) {
	    		error('只能选择一条记录');
	    		return;
	    	}
	    	if (!trs || trs.length != 1) {
	    		
	    	} 
	    	var thead = $(trs[0]).parent().parent().parent().parent().find('thead');
	    	var idIndex;
	    	var nameIndex;
	    	var ths = thead.find('tr>th');
	    	for (var i = 0; i < ths.length; i++) {
	    		var th = ths[i];
	    		if ($(th).attr('data-field') == 'id') {
	    			idIndex = i;
	    		} else if ($(th).attr('data-field') == name) {
	    			nameIndex = i;
	    		}
	    	}
	    	
	    	var tr = $(trs[0]).parent().parent();
	    	// var id = $(tr).find('td:eq(' + idIndex + ')').html();
	    	var id = $(tr).attr('data-id');
	    	var displayName = $(tr).find('td:eq(' + nameIndex + ')').html();
	    	if (idInput) {
	    		$('#' + idInput).val(id);
	    	}
	    	if (nameInput) {
	    		$('#' + nameInput).val(displayName);
	    	}
	    	parent.layer.close(index);
	    }, cancel: function(index){ //或者使用btn2
	        //按钮【按钮二】的回调
	    }
	});
}

function bringTree(options) {
	var url = options.url;
	var name = options.name;
	var idInput = options.idInput;
	var nameInput = options.nameInput;
	parent.layer.open({
	    type: 2,
	    skin: 'layui-layer-rim', //加上边框
	    area: [layer_default_width, layer_default_height], //宽高
	    content: url,
	    success: function(layero, index) {
	    	// 隐藏增删除查改等内容
	    	var operation = parent.layer.getChildFrame('#operation', index);
	    	$(operation).hide();
	    },
	    btn: ['选择', '取消'],
	    yes: function(index, layero){ //或者使用btn1
	    	var selectedId = parent.layer.getChildFrame('#selectedId', index);
	    	var selectedText = parent.layer.getChildFrame('#selectedText', index);
	    	$('#' + idInput).val($(selectedId).val());
	    	$('#' + nameInput).val($(selectedText).val());
	    	
	    	parent.layer.close(index);
	    }, cancel: function(index){ //或者使用btn2
	        //按钮【按钮二】的回调
	    }
	});
}

function bring4webuploader(options) {
	var single = options.single;
	var url = '../../res/image-upload/index.html';
	if (single) {
		url = '../../res/image-upload/index_1.html';	
	}
	var name = options.name;
	var idInput = options.idInput;
	parent.layer.open({
	    type: 2,
	    skin: 'layui-layer-rim', //加上边框
	    area: [layer_default_width, layer_default_height], //宽高
	    content: url,
	    success: function(layero, index) {
	    	
	    },
	    btn: ['选择', '取消'],
	    yes: function(index, layero){ //或者使用btn1
	    
	    	var img = parent.layer.getChildFrame("#imgUrl", index)
	    	$('#' + idInput).val($(img).html());
	    	$('#' + idInput).after('<image src="' + $(img).html() + '" width="100"/>');
	    	parent.layer.close(index);
	    }, cancel: function(index){ //或者使用btn2
	        //按钮【按钮二】的回调
	    }
	});
}

function bringSummernote(options) {
	var url = options.url;
	var name = options.name;
	var nameInput = options.nameInput;
	var testInputVal = $("#testInput").val();
	$.ajax({
	    url: url,    //请求的url地址
	    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
	    data: { "testInputVal": testInputVal },    //参数值
	    type: "POST",   //请求方式
	    contentType: "application/x-www-form-urlencoded;charset=UTF-8",
	    beforeSend: function() {
	        //请求前的处理
	    },
	    success: function(res) {
	        //请求成功时处理
	    	var lay = parent.layer.open({
	    		maxmin: true,
	    		area: ['600px', '600px'],
	    	    type: 1,
	    	    content: res,
	    	    btn: ['选择', '取消'],
	    	    yes: function(index, layero){ //或者使用btn1
	    	    
	    	    	alert($('#testDiv').html());
	    	    	$('#' + nameInput).val($(testDiv).html());
	    	    alert(index)
	    	        //parent.layer.close(index);
	    	    }, cancel: function(index){ //或者使用btn2
	    	        //按钮【按钮二】的回调
	    	    },
	    	  });
	    //  parent.layer.full(lay);
	    //	parent.layer.min(lay);
	    //	parent.layer.restore(lay);
	    },
	    
	    complete: function() {
	        //请求完成的处理
	    },
	    error: function() {
	        //请求出错处理
	    }
	});
}

function openLayer(url) {
	parent.layer.open({
	    type: 2,
	    skin: 'layui-layer-rim', //加上边框
	    area: [layer_default_width, layer_default_height], //宽高
	    content: url,
	    success: function(layero, index) {
	    	
	    },
	    btn: ['完成'],
	    yes: function(index, layero){ //或者使用btn1
	    	parent.layer.close(index);
	    }, cancel: function(index){ //或者使用btn2
	        //按钮【按钮二】的回调
	    }
	});
	return false;
}

function add_img(fileInputId) {
	$('#' + fileInputId).click();
	return false;
}
var loading_index
function loading() {
	loading_index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
}

function cancelLoading() {
	layer.close(loading_index);
}

function init_images(divId) {
	var div_my_images = $('#' + divId);
	var single = div_my_images.attr('single');
	var images = div_my_images.attr('images');
	var field = div_my_images.attr('field');
	if (images != '') {
		var arr = images.split(',');
		for (var i = 0; i < arr.length; i++) {
			var imageUrl = arr[i];
			var unit = build_image_unit(imageUrl, field, divId);
			div_my_images.append(unit);
		}
	}
	
	var add = '<div class="my-images-unit">';
	var fileInputId = divId + "-fileInput";
	add += '<input type="file" id="' + fileInputId + '" style="display: none;" onchange="image_upload_onchange(this, \'' + field + '\', \'' + divId + '\')"/>';
	add += '<a class="add-img" onclick="return add_img(\'' + fileInputId + '\');">添加</a>';
	add += '</div>';
	div_my_images.append(add);
}
function update_field(id, divId, add) {
	var div_my_images = $('#' + divId);
	var single = div_my_images.attr('single');
	
	var imgElements = div_my_images.find('img.my-img');
	var images = new Array();
	
	for (var i = 0; i < imgElements.length; i++) {
		images.push($(imgElements[i]).attr('src'));
	}
	var value = images.join(',');
	$('#' + id).val(value);
}
function image_upload_onchange(input, fieldId, divId) {
	var div_my_images = $('#' + divId);
	var single = div_my_images.attr('single');
	var imgElements = div_my_images.find('img.my-img');
	
	var fd = new FormData();
	fd.append("image", input.files[0]);
	if (single == 'true' && imgElements.length > 0) {
		error2('只允许添加一张图片');
		return;
	}
	loading();
	$.ajax({
		url: "../../image/upload7",
		type: "POST",
		processData: false,
		contentType: false,
		data: fd,
		success: function(imageUrl) {
			console.log(imageUrl);
			$(input).parent().append(build_image_unit(imageUrl, fieldId, divId));
			cancelLoading();
			update_field(fieldId, divId, true);
		}
	});
}

function del_img(a, fieldId, divId) {
	$(a).parent().remove();
	update_field(fieldId, divId);
}
function build_image_unit(imageUrl, fieldId, divId) {
	var unit = '<div class="my-images-unit">';
	unit += '<img src="' + imageUrl + '" class="my-img" alt="" />';
	unit += '<a class="del-img" onclick="return del_img(this, \'' + fieldId + '\', \'' + divId + '\')">删除</a>';
	unit += '</div>';
	return unit;
}
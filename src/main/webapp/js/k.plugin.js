var ctx = "";
$(document).ready(function() {
	processDateQueryType();
	$('select[data-name=DateQueryType]').change(function() {processDateQueryType()});
	// 省份
	$('select[data-type=province]').each(function() {
		var select = $(this);
		var value = $(this).attr('data-value');
		var citySelectId = $(this).attr('data-city');
		$.ajax({
			url: ctx + "/common/getProvinceList",
			dataType: "json",
			success: function(data) {
				var text="";
				if (!value) {
					select.append('<option value="">省份</option>');
					text+='<option value="">省份</option>';
				} else {
					select.append('<option value="" selected>省份</option>');
					text+='<option value="" selected>省份</option>';
				}
				for (var i = 0; i < data.length; i++) {
					var p = data[i];
					if (p.id == value) {
						var option = '<option value="' + p.id + '" selected>' + p.name + '</option>';
					} else {
						var option = '<option value="' + p.id + '">' + p.name + '</option>';
					}
					text+=option;
					select.append(option);
				}
				if(select[0].id=="merchantProvince"||select[0].id=="companyProvince"){
					$("#merchantProvince").append(text);
				}
				if(select[0].id=="companyProvince"){
					$("#companyProvince").append(text);
				}
				refreshCity(citySelectId, value);
				
			}
		});
	});
	
	// 枚举
	$('select[data-type=enum]').each(function() {
		var select = $(this);
		var value = $(this).attr('data-value');
		var name = $(this).attr('data-name');
		var required = "true" == $(this).attr('data-required');
		$.ajax({
			url: ctx + "/common/getEnum?name=" + name,
			dataType: "json",
			success: function(data) {
				if (!required) {
					if (!value && value != '') {
						select.append('<option value="">全部</option>');
					} else {
						select.append('<option value="" selected>全部</option>');
					}
				}
				for (var i = 0; i < data.length; i++) {
					var p = data[i];
					if (p.id === value) {
						var option = '<option value="' + p.id + '" selected>' + p.name + '</option>';
					} else {
						var option = '<option value="' + p.id + '">' + p.name + '</option>';
					}
					select.append(option);
				}
				
				processDateQueryType();
			}
		});
	});

    // 枚举
    $('select[data-type=enum2]').each(function() {
        var select = $(this);
        var value = $(this).attr('data-value');
        var name = $(this).attr('data-name');
        var required = "true" == $(this).attr('data-required');
        $.ajax({
            url: ctx + "/common/getEnum?name=" + name,
            dataType: "json",
            success: function(data) {
                if (!required) {
                    if (!value) {
                        select.append('<option value="">全部</option>');
                    } else {
                        select.append('<option value="" selected>全部</option>');
                    }
                }
                for (var i = 0; i < data.length; i++) {
                    var p = data[i];
                    if (p.id == value-1) {
                        var option = '<option value="' + p.id + '" selected>' + p.name + '</option>';
                    } else {
                        var option = '<option value="' + p.id + '">' + p.name + '</option>';
                    }
                    select.append(option);
                }

                processDateQueryType();
            }
        });
    });
	
});

//citySelectId: 城市的select对象的id
function refreshCity(citySelectId, provinceId) {
	var select = $('#' + citySelectId);
	if (provinceId == '' || provinceId == undefined ) {
		select.html('');
		select.append('<option value="">城市</option>');
		return;
	}
	var value = select.attr('data-value');
	$.ajax({
		url: ctx + "/common/getCityList?provinceId=" + provinceId,
		dataType: "json",
		success: function(data) {
			select.html('');
			if (!value) {
				select.append('<option value="">城市</option>');
			} else {
				select.append('<option value="" selected>城市</option>');
			}
			for (var i = 0; i < data.length; i++) {
				var p = data[i];
				if (p.id == value) {
					var option = '<option value="' + p.id + '" selected>' + p.name + '</option>';
				} else {
					var option = '<option value="' + p.id + '">' + p.name + '</option>';
				}
				select.append(option);
			}
			
		}
	});
}

function processDateQueryType() {
	$('select[data-name=DateQueryType]').each(function() {
		var select = $(this);
		var startDateId = $(this).attr('data-startDate');
		var endDateId = $(this).attr('data-endDate');
		if ($(this).val() == 5) { // 自定义
			if (startDateId) {
				$('#' + startDateId).show();
			}
			if (endDateId) {
				$('#' + endDateId).show();
			}
		} else {
			if (startDateId) {
				$('#' + startDateId).hide();
			}
			if (endDateId) {
				$('#' + endDateId).hide();
			}
		}
	});
}
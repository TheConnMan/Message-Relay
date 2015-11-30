function getData() {
	$.ajax({
		url: '/graph/data',
		data: {
			clientId: $('#clientId').val()
		},
		success: renderData
	});
}

function renderData(data) {
	var format = d3.time.format('%m/%d/%Y %H:%M')
	var x = data.map(function(d) {
		return format(new Date(d.dateCreated));
	});
	var y = data.map(function(d) {
		return d.value;
	});
	c3.generate({
		bindto: '#graph',
		data: {
			x: 'x',
			xFormat: '%m/%d/%Y %H:%M',
			columns: [
				['x'].concat(x),
				[data.length != 0 ? data[0].clientId : 'No Data'].concat(y)
			]
		},
		axis: {
			x: {
				type: 'timeseries',
				tick: {
					format: '%Y-%m-%d %H:%M'
				}
			}
		},
		point: {
			show: false
		}
	});
}
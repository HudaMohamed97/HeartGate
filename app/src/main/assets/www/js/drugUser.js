/*global $, window, alert, TweenMax, console, document, FormData, AndroidFunction, FileReader, changeProfile, navigator, setTimeout, location, XMLHttpRequest, setInterval */

$(function () {
	
	'use strict';
	
	var isDevice = (/android|webos|iphone|ipad|ipod|blackberry/i.test(navigator.userAgent.toLowerCase())),
		interactionUp,
		interactionDown,
		interactionOver,
		interactionMove,
		userid,
        username,
		sel1 = document.getElementById('sel1'),
		sel2 = document.getElementById('sel2'),
		sel3 = document.getElementById('sel3'),
		showRes = document.getElementById('showRes'),
		major = '<span class="major">Major</span><p>Highly clinically significant. Avoid combinations; the risk of the interaction outweighs the benefit.</p>',
		moderate = '<span class="moderate">Moderate</span><p>Moderately clinically significant. Usually avoid combinations; use it only under special circumstances.</p>',
		minor = '<span class="minor">Minor</span><p>Minimally clinically significant. Minimize risk; assess risk and consider an alternative drug, take steps to circumvent the interaction risk and/or institute a monitoring plan.</p>',
		noresult = '<span class="noresult">No result</span><p>No results found - however, this does not necessarily mean no interactions exist. Always consult with your doctor or pharmacist.</p>',
        gender,
        speciality,
        job,
        gov,
        experience,
        lookups,
        google;
	
	if (isDevice) {
		interactionUp = "touchend";
		interactionDown = "touchstart";
		interactionOver = interactionDown;
		interactionMove = 'touchmove';
	} else {
		interactionUp = "click";
		interactionDown = "mousedown";
		interactionOver = "mouseover";
		interactionMove = 'mousemove';

	}


	/*------************-----------START--------****************-------*/
// fill DDI select box
function ddiOptions() {

		$.ajax({

			url: 'http://heartgate.co/api_heartgate/drugs_all',
			type: 'get',
			success: function (ddi) {
				//console.log(ddi);
				var ddiArr = JSON.parse(ddi),
					i;

				$('.ddiSpan .ui-select .ui-btn span').append("acebutolol");

				for (i in ddiArr) {
					if (ddiArr.hasOwnProperty(i)) {
						$('#sel1 optgroup').append('<option value="' + ddiArr[i].DRUG_ID + '">' + ddiArr[i].NAME + '</option>');
						$('#sel2 optgroup').append('<option value="' + ddiArr[i].DRUG_ID + '">' + ddiArr[i].NAME + '</option>');
						$('#sel3 optgroup').append('<option value="' + ddiArr[i].DRUG_ID + '">' + ddiArr[i].NAME + '</option>');
					} // end if condition
				} // end for loop
			},
			processData: false,
			contentType: false

		}); // end AJAX

	} // end function ddiOptions()

	$(document).on(interactionUp, '#btnOfDDI', function () {
		setTimeout(function () {
			ddiOptions();
			$(this).fadeOut(200).fadeIn(200);
		}, 800);

	});

	ddiOptions();






	$(document).on(interactionUp, '#btnOfDDI', ddiOptions).fadeOut(200).fadeIn(200);




	
});

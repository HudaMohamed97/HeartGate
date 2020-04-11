/*global $, alert, TweenMax, console */

$(function () {
	'use strict';
	/*------************-----------START--------****************-------*/

	var scrTbl = [
		{
			Male: {
				NonSmoke: {
					Age40: {
						'105': [0, 0, 1, 1],
						'150': [0, 1, 1, 1],
						'200': [1, 1, 1, 1],
						'250': [1, 1, 1, 2],
						'300': [1, 1, 1, 2],
					},
					Age50: {
						'105': [1, 2, 2, 4],
						'150': [1, 2, 3, 4],
						'200': [2, 2, 3, 5],
						'250': [2, 3, 4, 6],
						'300': [2, 3, 5, 7],
					},
					Age55: {
						'105': [2, 3, 4, 6],
						'150': [2, 3, 5, 7],
						'200': [3, 4, 6, 8],
						'250': [3, 5, 7, 10],
						'300': [4, 6, 8, 12],
					},
					Age60: {
						'105': [3, 4, 6, 9],
						'150': [3, 5, 7, 11],
						'200': [4, 6, 9, 13],
						'250': [5, 7, 10, 15],
						'300': [6, 9, 12, 18],
					},
					Age65: {
						'105': [4, 6, 9, 14],
						'150': [5, 8, 11, 16],
						'200': [6, 9, 13, 19],
						'250': [7, 11, 15, 22],
						'300': [9, 13, 16, 26],
					},
				},
				Smoke: {
					Age40: {
						'105': [1, 1, 1, 2],
						'150': [1, 1, 2, 2],
						'200': [1, 1, 2, 3],
						'250': [1, 2, 2, 3],
						'300': [1, 2, 3, 4],
					},
					Age50: {
						'105': [2, 3, 5, 7],
						'150': [3, 4, 6, 8],
						'200': [3, 5, 7, 10],
						'250': [4, 6, 8, 12],
						'300': [5, 7, 10, 14],
					},
					Age55: {
						'105': [4, 5, 8, 12],
						'150': [4, 6, 9, 13],
						'200': [5, 8, 11, 16],
						'250': [6, 9, 13, 19],
						'300': [8, 11, 16, 22],
					},
					Age60: {
						'105': [6, 8, 12, 18],
						'150': [7, 10, 14, 21],
						'200': [8, 12, 17, 24],
						'250': [10, 14, 20, 28],
						'300': [12, 17, 24, 33],
					},
					Age65: {
						'105': [9, 13, 18, 26],
						'150': [10, 15, 21, 30],
						'200': [12, 17, 25, 35],
						'250': [14, 20, 29, 41],
						'300': [17, 24, 34, 47],
					},
				},
			},
			Female: {
				NonSmoke: {
					Age40: {
						'105': [0, 0, 0, 0],
						'150': [0, 0, 0, 0],
						'200': [0, 0, 0, 0],
						'250': [0, 0, 0, 0],
						'300': [0, 0, 0, 0],
					},
					Age50: {
						'105': [0, 0, 1, 1],
						'150': [0, 1, 1, 1],
						'200': [1, 1, 1, 1],
						'250': [1, 1, 1, 2],
						'300': [1, 1, 1, 2],
					},
					Age55: {
						'105': [1, 1, 1, 2],
						'150': [1, 1, 2, 2],
						'200': [1, 1, 2, 3],
						'250': [1, 1, 2, 3],
						'300': [1, 2, 3, 4],
					},
					Age60: {
						'105': [1, 2, 3, 4],
						'150': [1, 2, 3, 4],
						'200': [2, 2, 3, 4],
						'250': [2, 3, 4, 6],
						'300': [2, 3, 5, 7],
					},
					Age65: {
						'105': [2, 3, 5, 7],
						'150': [2, 3, 5, 8],
						'200': [3, 4, 6, 9],
						'250': [3, 5, 7, 10],
						'300': [4, 6, 8, 12],
					},
				},
				Smoke: {
					Age40: {
						'105': [0, 0, 0, 0],
						'150': [0, 0, 0, 0],
						'200': [0, 0, 0, 0],
						'250': [0, 0, 0, 1],
						'300': [0, 0, 0, 1],
					},
					Age50: {
						'105': [1, 1, 1, 2],
						'150': [1, 1, 2, 2],
						'200': [1, 1, 2, 3],
						'250': [1, 1, 2, 3],
						'300': [1, 2, 3, 4],
					},
					Age55: {
						'105': [1, 2, 3, 4],
						'150': [1, 2, 3, 5],
						'200': [2, 2, 4, 5],
						'250': [2, 3, 4, 6],
						'300': [2, 3, 5, 7],
					},
					Age60: {
						'105': [2, 3, 5, 8],
						'150': [3, 4, 6, 9],
						'200': [3, 5, 7, 10],
						'250': [4, 5, 8, 11],
						'300': [4, 6, 9, 13],
					},
					Age65: {
						'105': [2, 3, 5, 7],
						'150': [2, 3, 5, 8],
						'200': [3, 4, 6, 9],
						'250': [3, 5, 7, 10],
						'300': [4, 6, 8, 12],
					},
				},
			},
		},
	];
	$('#calcBMI').on('click', function () {
		var weight = $('#weight').val(),
			height = $('#height').val(),
			calcBMI = $('#calcBMI'),
			resultBMI = $('#resultBMI'),
			expV = Math.pow(height, 2),
			numValue = weight / expV;
		//console.log(numValue);
		$(resultBMI)
			.empty()
			.append('The Result: <span style="color:#dbed26; font-size: 130%;">' + numValue.toFixed(2));

		if (numValue < 18.5) {
			$('.bmi .td1').addClass('tr').fadeOut(200).fadeIn(200).fadeOut(200).fadeIn(200);
			$('.bmi .td2').removeClass('tr');
			$('.bmi .td3').removeClass('tr');
			$('.bmi .td4').removeClass('tr');
		} else if (numValue > 18.5 && numValue <= 24.9) {
			$('.bmi .td1').removeClass('tr');
			$('.bmi .td2').addClass('tr').fadeOut(200).fadeIn(200).fadeOut(200).fadeIn(200);
			$('.bmi .td3').removeClass('tr');
			$('.bmi .td4').removeClass('tr');
		} else if (numValue > 24.9 && numValue <= 29.9) {
			$('.bmi .td1').removeClass('tr');
			$('.bmi .td2').removeClass('tr');
			$('.bmi .td3').addClass('tr').fadeOut(200).fadeIn(200).fadeOut(200).fadeIn(200);
			$('.bmi .td4').removeClass('tr');
		} else if (numValue > 29.9) {
			$('.bmi .td1').removeClass('tr');
			$('.bmi .td2').removeClass('tr');
			$('.bmi .td3').removeClass('tr');
			$('.bmi .td4').addClass('tr').fadeOut(200).fadeIn(200).fadeOut(200).fadeIn(200);
		}
	});

	function cvdEstimateRisk() {
		// Calculate the risk factor of cvd
		var age = $('input[id=age]', '#cvdCalc').val() ? $('input[id=age]', '#cvdCalc').val() : 0,
			smoke = $('option[name=smoke]:checked', '#cvdCalc').val(),
			cvd = $('option[name=cvd]:checked', '#cvdCalc').val(),
			sbp = $('#SBP').val(),
			tc = $('#TC').val(),
			ldl = $('#ldl-c').val(),
			gender = $('option[name=gender]:checked', '#cvdCalc').val(),
			ckd = $('option[name=ckd]:checked', '#cvdCalc').val(),
			sc = $('#SC').val() == '' ? 0 : $('#SC').val(),
			diabetic = $('option[name=diabetic]:checked', '#cvdCalc').val(),
			albumin = $('#albumin').val(),
			preprandial = $('#preprandial').val(),
			postprandial = $('#postprandial').val(),
			a1c = $('#A1C').val(),
			gfrMale = parseInt(Math.round(175 * Math.pow(sc, -1.154) * Math.pow(age, -0.203)).toFixed(2), 10),
			gfrFemale = gfrMale * (0.742).toFixed(2),
			gfr = gender === 'Male' ? gfrMale : gfrFemale,
			totalScore;

		function patientData() {
			$('#sbpResult1')
				.empty()
				.append(sbp + ' mmHg');
			$('#sbpResult2').empty().append('130-139 mmHg');

			$('#tcResult1')
				.empty()
				.append(tc + ' mg/dl');
			$('#tcResult2').empty().append('<190 mg/dl');

			$('#ldlResult1')
				.empty()
				.append(ldl + ' mg/dl');
			$('#ldlResult2').empty().append('<70 mg/dl');

			$('#preResult1')
				.empty()
				.append(preprandial + ' mg/dl');
			$('#preResult2').empty().append('70-130 mg/dl');

			$('#postResult1')
				.empty()
				.append(postprandial + ' mg/dl');
			$('#postResult2').empty().append('<180 mg/dl');

			$('#a1cResult1')
				.empty()
				.append(a1c + ' %');
			$('#a1cResult2').empty().append('7% or less');
		} // End Function of Estimate Risk
		patientData();

		function tcFunction(tc1, tc2, deg) {
			if (tc >= tc1 && tc < tc2) {
				$('#totalScore').empty().append(deg);
			}
			totalScore = deg;
		}
		function calcRiskScore(gender, smoke, age1, age2, sbp1, sbp2, tcFunc) {
			if (gender === gender && smoke === smoke) {
				if (age >= age1 && age < age2) {
					if (sbp >= sbp1 && sbp < sbp2) {
						tcFunc;
					}
				}
			}
		}

		for (let i = 0; i < 4; i++) {
			calcRiskScore(
				'Male',
				'No',
				40,
				50,
				100,
				120,
				tcFunction(105, 150, scrTbl[0].Male.NonSmoke.Age40['105'][i])
			);
		}
		for (let i = 0; i < 4; i++) {
			calcRiskScore(
				'Male',
				'No',
				40,
				50,
				100,
				120,
				tcFunction(105, 150, scrTbl[0].Male.NonSmoke.Age40['105'][i])
			);
		}

		if (cvd == 'Yes' || gfr < 30) {
			$('#cvdResult1').empty().append('Very High');
			$('#totalScore').empty().append(totalScore);
		}
		if (cvd == 'Yes' && diabetic == 'Yes') {
			$('#cvdResult1').empty().append('Very High');
			$('#totalScore').empty().append(totalScore);
		}
		if (ckd == 'Yes' && diabetic == 'Yes') {
			$('#cvdResult1').empty().append('Very High');
			$('#totalScore').empty().append(totalScore);
		}
		if (diabetic == 'Yes' && albumin > 30) {
			$('#cvdResult1').empty().append('Very High');
			$('#totalScore').empty().append(totalScore);
		}
		if (totalScore >= 10) {
			$('#cvdResult1').empty().append('Very High');
			$('#totalScore').empty().append(totalScore);
		}

		if (
			tc > 305 ||
			tc == 'More...' ||
			sbp > 180 ||
			sbp == 'More...' ||
			diabetic == 'Yes' ||
			(gfr >= 30 && gfr < 60) ||
			(totalScore >= 5 && totalScore < 10)
		) {
			$('#cvdResult1').empty().append('High');
			$('#totalScore').empty().append(totalScore);
		}

		if (totalScore >= 1 && totalScore < 5) {
			$('#cvdResult1').empty().append('Moderate');
			$('#totalScore').empty().append(totalScore);
		}
		if (totalScore < 1) {
			$('#cvdResult1').empty().append('Low');
			$('#totalScore').empty().append(totalScore);
		}
		function gfrCalc() {
			if (gfr < 30) {
				$('#gfrResult1')
					.empty()
					.append(gfr + ' mL/min/1.73m<sup>2</sup>');
				$('#gfrResult2').empty().append('Severe chronic kidney disease');
			} else if (gfr >= 30 && gfr < 60) {
				$('#gfrResult1')
					.empty()
					.append(gfr + ' mL/min/1.73m<sup>2</sup>');
				$('#gfrResult2').empty().append('Moderate chronic kidney disease');
			} else if (gfr > 60) {
				$('#gfrResult1')
					.empty()
					.append(gfr + ' mL/min/1.73m<sup>2</sup>');
				$('#gfrResult2').empty().append('Normal');
			}
		}
		gfrCalc();

		console.log(
			'Age: ' +
				age +
				'\n' +
				'Gender: ' +
				gender +
				'\n' +
				'Smoke: ' +
				smoke +
				'\n' +
				'\n' +
				'CVD: ' +
				cvd +
				'\n' +
				'CKD: ' +
				ckd +
				'\n' +
				'Diabetic: ' +
				diabetic +
				'\n' +
				'Total Chlosterol: ' +
				tc +
				'\n' +
				'SBP: ' +
				sbp +
				'\n' +
				'SC: ' +
				sc +
				'\n' +
				'LDL-C: ' +
				ldl +
				'\n' +
				'Albumin: ' +
				albumin +
				'\n' +
				'pre: ' +
				preprandial +
				'\n' +
				'post: ' +
				postprandial +
				'\n' +
				'A1C: ' +
				a1c +
				'\n' +
				'GFR: ' +
				gfr +
				'-' +
				gfrMale +
				'-' +
				gfrFemale
		);
	} // End cvdEstimateRisk function (Cardio_risk.html file)...

	$('#cvdCalc #estimate').on('click', cvdEstimateRisk);
}); // End of ready doc

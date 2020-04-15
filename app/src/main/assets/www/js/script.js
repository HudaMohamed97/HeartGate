/*global $, alert, TweenMax, console */

$(function () {
	('use strict');
	/*------************-----------START--------****************-------*/

	var scrTbl = [
		{
			ID: 1,
			Gender: 'Female',
			Smoke: 'No',
			Age: [40, 50],
			SBP: [
				[99, 130],
				[130, 150],
				[150, 170],
				[170, 200],
			],
			TC: [
				[104, 150],
				[150, 200],
				[200, 250],
				[250, 300],
				[300, 350],
			],
			Score: [
				[0, 0, 0, 0, 0],
				[0, 0, 0, 0, 0],
				[0, 0, 0, 0, 0],
				[0, 0, 0, 0, 0],
			],
		},
		{
			ID: 2,
			Gender: 'Female',
			Smoke: 'No',
			Age: [50, 55],
			SBP: [
				[99, 130],
				[130, 150],
				[150, 170],
				[170, 200],
			],
			TC: [
				[104, 150],
				[150, 200],
				[200, 250],
				[250, 300],
				[300, 350],
			],
			Score: [
				[0, 0, 1, 1, 1],
				[0, 1, 1, 1, 1],
				[1, 1, 1, 1, 1],
				[1, 1, 1, 2, 2],
			],
		},
		{
			ID: 3,
			Gender: 'Female',
			Smoke: 'No',
			Age: [55, 60],
			SBP: [
				[99, 130],
				[130, 150],
				[150, 170],
				[170, 200],
			],
			TC: [
				[104, 150],
				[150, 200],
				[200, 250],
				[250, 300],
				[300, 350],
			],
			Score: [
				[1, 1, 1, 1, 1],
				[1, 1, 1, 1, 2],
				[1, 2, 2, 2, 3],
				[2, 2, 3, 3, 4],
			],
		},
		{
			ID: 4,
			Gender: 'Female',
			Smoke: 'No',
			Age: [60, 65],
			SBP: [
				[99, 130],
				[130, 150],
				[150, 170],
				[170, 200],
			],
			TC: [
				[104, 150],
				[150, 200],
				[200, 250],
				[250, 300],
				[300, 350],
			],
			Score: [
				[1, 1, 2, 2, 2],
				[2, 2, 2, 3, 3],
				[3, 3, 3, 4, 5],
				[4, 4, 5, 6, 7],
			],
		},
		{
			ID: 5,
			Gender: 'Female',
			Smoke: 'No',
			Age: [65, 150],
			SBP: [
				[99, 130],
				[130, 150],
				[150, 170],
				[170, 200],
			],
			TC: [
				[104, 150],
				[150, 200],
				[200, 250],
				[250, 300],
				[300, 350],
			],
			Score: [
				[2, 2, 3, 3, 4],
				[3, 3, 4, 5, 6],
				[5, 5, 6, 7, 8],
				[7, 8, 9, 10, 12],
			],
		},
		{
			ID: 6,
			Gender: 'Female',
			Smoke: 'Yes',
			Age: [40, 50],
			SBP: [
				[99, 130],
				[130, 150],
				[150, 170],
				[170, 200],
			],
			TC: [
				[104, 150],
				[150, 200],
				[200, 250],
				[250, 300],
				[300, 350],
			],
			Score: [
				[0, 0, 0, 0, 0],
				[0, 0, 0, 0, 0],
				[0, 0, 0, 0, 0],
				[0, 0, 0, 1, 1],
			],
		},
		{
			ID: 7,
			Gender: 'Female',
			Smoke: 'Yes',
			Age: [50, 55],
			SBP: [
				[99, 130],
				[130, 150],
				[150, 170],
				[170, 200],
			],
			TC: [
				[104, 150],
				[150, 200],
				[200, 250],
				[250, 300],
				[300, 350],
			],
			Score: [
				[1, 1, 1, 1, 1],
				[1, 1, 1, 1, 2],
				[1, 2, 2, 2, 3],
				[2, 2, 3, 3, 4],
			],
		},
		{
			ID: 8,
			Gender: 'Female',
			Smoke: 'Yes',
			Age: [55, 60],
			SBP: [
				[99, 130],
				[130, 150],
				[150, 170],
				[170, 200],
			],
			TC: [
				[104, 150],
				[150, 200],
				[200, 250],
				[250, 300],
				[300, 350],
			],
			Score: [
				[1, 1, 2, 2, 2],
				[2, 2, 2, 3, 3],
				[3, 3, 4, 4, 5],
				[4, 5, 5, 6, 7],
			],
		},
		{
			ID: 9,
			Gender: 'Female',
			Smoke: 'Yes',
			Age: [60, 65],
			SBP: [
				[99, 130],
				[130, 150],
				[150, 170],
				[170, 200],
			],
			TC: [
				[104, 150],
				[150, 200],
				[200, 250],
				[250, 300],
				[300, 350],
			],
			Score: [
				[2, 3, 3, 4, 4],
				[3, 4, 5, 5, 6],
				[5, 6, 7, 8, 9],
				[8, 9, 10, 11, 13],
			],
		},
		{
			ID: 10,
			Gender: 'Female',
			Smoke: 'Yes',
			Age: [65, 150],
			SBP: [
				[99, 130],
				[130, 150],
				[150, 170],
				[170, 200],
			],
			TC: [
				[104, 150],
				[150, 200],
				[200, 250],
				[250, 300],
				[300, 350],
			],
			Score: [
				[4, 5, 5, 6, 7],
				[6, 7, 8, 9, 11],
				[9, 10, 12, 13, 16],
				[13, 15, 17, 19, 22],
			],
		},
		{
			ID: 11,
			Gender: 'Male',
			Smoke: 'No',
			Age: [40, 50],
			SBP: [
				[99, 130],
				[130, 150],
				[150, 170],
				[170, 200],
			],
			TC: [
				[104, 150],
				[150, 200],
				[200, 250],
				[250, 300],
				[300, 350],
			],
			Score: [
				[0, 0, 1, 1, 1],
				[0, 1, 1, 1, 1],
				[1, 1, 1, 1, 1],
				[1, 1, 1, 2, 2],
			],
		},
		{
			ID: 12,
			Gender: 'Male',
			Smoke: 'No',
			Age: [50, 55],
			SBP: [
				[99, 130],
				[130, 150],
				[150, 170],
				[170, 200],
			],
			TC: [
				[104, 150],
				[150, 200],
				[200, 250],
				[250, 300],
				[300, 350],
			],
			Score: [
				[1, 1, 2, 2, 2],
				[2, 2, 2, 3, 3],
				[2, 3, 3, 4, 5],
				[4, 4, 5, 6, 7],
			],
		},
		{
			ID: 13,
			Gender: 'Male',
			Smoke: 'No',
			Age: [55, 60],
			SBP: [
				[99, 130],
				[130, 150],
				[150, 170],
				[170, 200],
			],
			TC: [
				[104, 150],
				[150, 200],
				[200, 250],
				[250, 300],
				[300, 350],
			],
			Score: [
				[2, 2, 3, 3, 4],
				[3, 3, 4, 5, 6],
				[4, 5, 6, 7, 8],
				[6, 7, 8, 10, 12],
			],
		},
		{
			ID: 14,
			Gender: 'Male',
			Smoke: 'No',
			Age: [60, 65],
			SBP: [
				[99, 130],
				[130, 150],
				[150, 170],
				[170, 200],
			],
			TC: [
				[104, 150],
				[150, 200],
				[200, 250],
				[250, 300],
				[300, 350],
			],
			Score: [
				[3, 3, 4, 5, 6],
				[4, 5, 6, 7, 9],
				[6, 7, 9, 10, 12],
				[9, 11, 13, 15, 18],
			],
		},
		{
			ID: 15,
			Gender: 'Male',
			Smoke: 'No',
			Age: [65, 150],
			SBP: [
				[99, 130],
				[130, 150],
				[150, 170],
				[170, 200],
			],
			TC: [
				[104, 150],
				[150, 200],
				[200, 250],
				[250, 300],
				[300, 350],
			],
			Score: [
				[4, 5, 6, 7, 9],
				[6, 8, 9, 11, 13],
				[9, 11, 13, 15, 16],
				[14, 16, 19, 22, 26],
			],
		},
		{
			ID: 16,
			Gender: 'Male',
			Smoke: 'Yes',
			Age: [40, 50],
			SBP: [
				[99, 130],
				[130, 150],
				[150, 170],
				[170, 200],
			],
			TC: [
				[104, 150],
				[150, 200],
				[200, 250],
				[250, 300],
				[300, 350],
			],
			Score: [
				[1, 1, 1, 1, 1],
				[1, 1, 1, 2, 2],
				[1, 2, 2, 2, 3],
				[2, 2, 3, 3, 4],
			],
		},
		{
			ID: 17,
			Gender: 'Male',
			Smoke: 'Yes',
			Age: [50, 55],
			SBP: [
				[99, 130],
				[130, 150],
				[150, 170],
				[170, 200],
			],
			TC: [
				[104, 150],
				[150, 200],
				[200, 250],
				[250, 300],
				[300, 350],
			],
			Score: [
				[2, 3, 3, 4, 5],
				[3, 4, 5, 6, 7],
				[5, 6, 7, 8, 10],
				[7, 8, 10, 12, 14],
			],
		},
		{
			ID: 18,
			Gender: 'Male',
			Smoke: 'Yes',
			Age: [55, 60],
			SBP: [
				[99, 130],
				[130, 150],
				[150, 170],
				[170, 200],
			],
			TC: [
				[104, 150],
				[150, 200],
				[200, 250],
				[250, 300],
				[300, 350],
			],
			Score: [
				[4, 4, 5, 6, 8],
				[5, 6, 8, 9, 11],
				[8, 9, 11, 13, 16],
				[12, 13, 16, 19, 22],
			],
		},
		{
			ID: 19,
			Gender: 'Male',
			Smoke: 'Yes',
			Age: [60, 65],
			SBP: [
				[99, 130],
				[130, 150],
				[150, 170],
				[170, 200],
			],
			TC: [
				[104, 150],
				[150, 200],
				[200, 250],
				[250, 300],
				[300, 350],
			],
			Score: [
				[6, 7, 8, 10, 12],
				[8, 10, 12, 14, 17],
				[12, 14, 17, 20, 24],
				[18, 21, 24, 28, 33],
			],
		},
		{
			ID: 20,
			Gender: 'Male',
			Smoke: 'Yes',
			Age: [65, 150],
			SBP: [
				[99, 130],
				[130, 150],
				[150, 170],
				[170, 200],
			],
			TC: [
				[104, 150],
				[150, 200],
				[200, 250],
				[250, 300],
				[300, 350],
			],
			Score: [
				[9, 10, 12, 14, 17],
				[13, 15, 17, 20, 24],
				[18, 21, 25, 29, 34],
				[26, 30, 35, 41, 47],
			],
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
			sc = $('#SC').val() !== '' ? $('#SC').val() : undefined,
			diabetic = $('option[name=diabetic]:checked', '#cvdCalc').val(),
			albumin = $('#albumin').val(),
			preprandial = $('#preprandial').val(),
			postprandial = $('#postprandial').val(),
			a1c = $('#A1C').val(),
			gfrMale = parseFloat(Math.round(175 * Math.pow(sc, -1.154) * Math.pow(age, -0.203)).toFixed(2)),
			gfrFemale = gfrMale * (0.742).toFixed(2),
			gfr = gender === 'Male' ? gfrMale : gfrFemale,
			totalScore;

		(function patientData() {
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
		})();

		function gfrCalc() {
			if (sc === '' || sc === undefined || sc === 0 || gfr === Infinity) {
				$('#gfrResult1').empty().append('Empty ...mL/min/1.73m<sup>2</sup>');
				$('#gfrResult2').empty().append('No kidney disease');
			}
			if (gfr < 30) {
				$('#cvdResult1').empty().append('Very High');
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

		function calcTheLevels() {
			if (cvd == 'Yes' || gfr < 30) {
				$('#cvdResult1').empty().append('Very High');
				$('#totalScore').empty().append(totalScore);
			} else if (cvd == 'Yes' && diabetic == 'Yes') {
				$('#cvdResult1').empty().append('Very High');
				$('#totalScore').empty().append(totalScore);
			} else if (ckd == 'Yes' && diabetic == 'Yes') {
				$('#cvdResult1').empty().append('Very High');
				$('#totalScore').empty().append(totalScore);
			} else if (diabetic == 'Yes' && albumin > 30) {
				$('#cvdResult1').empty().append('Very High');
				$('#totalScore').empty().append(totalScore);
			} else if (totalScore >= 10) {
				$('#cvdResult1').empty().append('Very High');
				$('#totalScore').empty().append(totalScore);
			} else if (parseInt($('#totalScore').text()) >= 10) {
				$('#cvdResult1').empty().append('Very High');
				$('#totalScore').empty().append(totalScore);
			} else if ((sbp >= 180 && tc >= 300) || (sbp === 'More...' && tc === 'More...')) {
				$('#cvdResult1').empty().append('Very High');
				$('#totalScore').empty().append(totalScore);
			} else if (
				tc > 305 ||
				tc === 'More...' ||
				sbp > 180 ||
				sbp === 'More...' ||
				diabetic === 'Yes' ||
				(gfr >= 30 && gfr < 60)
			) {
				$('#cvdResult1').empty().append('High');
				$('#totalScore').empty().append(totalScore);
			} else if (totalScore < 1 || (age < 40 && tc < 150 && sbp < 120)) {
				$('#cvdResult1').empty().append('No Risk');
				$('#totalScore').empty().append('0');
			} else if (gfr === Infinity) {
				$('#gfrResult1').empty().append('Empty ...mL/min/1.73m<sup>2</sup>');
				$('#gfrResult2').empty().append('Normal');
			}
		}
		// if (age >= 65) {
		// 	$('#cvdResult1').empty().append('Very High');
		// 	$('#totalScore').empty().append(totalScore);
		// }
		// console.log(scrTbl[14].Age[0]);
		function calcRiskScore() {
			for (let g = 0; g < 20; g++) {
				if (gender === scrTbl[g].Gender && smoke === scrTbl[g].Smoke) {
					if (age >= scrTbl[g].Age[0] && age < scrTbl[g].Age[1]) {
						for (let s = 0; s < 4; s++) {
							if (sbp > scrTbl[g].SBP[s][0] && sbp <= scrTbl[g].SBP[s][1]) {
								for (let c = 0; c < 5; c++) {
									if (tc >= scrTbl[g].TC[c][0] && tc < scrTbl[g].TC[c][1]) {
										totalScore = scrTbl[g].Score[s][c];
										$('#totalScore').empty().append(totalScore);

										console.log(totalScore + ' : %');

										if (totalScore >= 40) {
											$('#cvdResult1').empty().append('Very High');
											$('#totalScore').empty().append('Total Score > 40');
										} else if (totalScore >= 10 && totalScore < 47) {
											$('#cvdResult1').empty().append('Very High');
											$('#totalScore').empty().append(totalScore);
										} else if (totalScore >= 10 && totalScore < 47) {
											$('#cvdResult1').empty().append('Very High');
											$('#totalScore').empty().append(totalScore);
										} else if (totalScore >= 5 && totalScore < 10) {
											$('#cvdResult1').empty().append('High');
											$('#totalScore').empty().append(totalScore);
										} else if (totalScore >= 1 && totalScore < 5) {
											$('#cvdResult1').empty().append('Moderate');
											$('#totalScore').empty().append(totalScore);
										} else if (totalScore < 1) {
											$('#cvdResult1').empty().append('Low');
											$('#totalScore').empty().append('0');
										}
									}
								}
							}
						}
					}
				}
			}
		}

		if (age == '' || age == undefined) {
			alert('Kindly add the patient age!');
			return false;
		} else if (gender == '' || gender == undefined) {
			alert('Kindly add the patient gender!');
			return false;
		} else if (smoke == '' || smoke == undefined) {
			alert('Kindly choose if patient is smoker or not!');
			return false;
		} else {
			calcRiskScore();
			calcTheLevels();
			gfrCalc();
			setTimeout(function () {
				if ($('#cvdResult1').text() === 'Very High') {
					$('#ldlResult2').empty().append('<70 mg/dl');
				}
				if ($('#cvdResult1').text() === 'High') {
					$('#ldlResult2').empty().append('<100 mg/dl');
				}
				if ($('#cvdResult1').text() === 'Very High' || $('#cvdResult1').text() === 'High') {
					TweenMax.fromTo(
						$('#cvdResult1'),
						1.2,
						{
							fontSize: '1.2em',
							color: 'darkred',
							opacity: 0.85,
							fontWeight: 'normal',
						},
						{
							fontSize: '1.8em',
							color: 'red',
							opacity: 1,
							fontWeight: 'bold',
							repeat: -1,
							yoyo: true,
						}
					);
				} else if (
					$('#cvdResult1').text() === 'Moderate' ||
					$('#cvdResult1').text() === 'Low' ||
					$('#cvdResult1').text() === 'No Risk' ||
					gfr === Infinity
				) {
					$('#ldlResult2').empty().append('<115 mg/dl');
					TweenMax.to($('#cvdResult1'), 1, {
						fontSize: '1em',
						color: '#31708f',
						opacity: 1,
						fontWeight: 'normal',
					});
					$('#gfrResult1').empty().append('Empty ...mL/min/1.73m<sup>2</sup>');
					$('#gfrResult2').empty().append('Normal');
				}
			}, 400);
		}
	} // End cvdEstimateRisk function (Cardio_risk.html file)...

	$('#cvdCalc #estimate').on('click', cvdEstimateRisk);
}); // End of ready doc

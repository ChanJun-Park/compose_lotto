package com.jingom.composelotto.ui.lotto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jingom.composelotto.R
import com.jingom.composelotto.support.util.DisplayUtils
import com.jingom.composelotto.ui.model.LottoResult
import com.jingom.composelotto.ui.theme.LottoNumberSurfaceBlack
import com.jingom.composelotto.ui.theme.LottoNumberYellow
import com.jingom.composelotto.ui.util.LottoNumberUtil
import com.jingom.composelotto.ui.util.LottoNumberUtil.LOTTO_BALL_COUNT

@Composable
fun LottoBall(number: Int) {
	Surface(
		modifier = Modifier.size(dimensionResource(R.dimen.lotto_ball_width)),
		shape = CircleShape,
		color = LottoNumberUtil.getLottoBallColor(number)
	) {
		Row(
			modifier = Modifier.fillMaxSize(),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.Center
		) {
			Text(
				text = "$number",
				modifier = Modifier
					.background(LottoNumberSurfaceBlack)
					.padding(horizontal = dimensionResource(R.dimen.lotto_ball_number_padding)),
				color = LottoNumberYellow
			)
		}
	}
}

@Composable
fun LottoBallCell(
	modifier: Modifier = Modifier,
	number: Int
) {
	Row(
		modifier = modifier,
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Center
	) {
		LottoBall(number)
	}
}

@Composable
fun BonusNumberDescription() {
	Row(
		modifier = Modifier.size(dimensionResource(id = R.dimen.lotto_ball_width)),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Center
	) {
		Text(
			text = "+ ${stringResource(R.string.bonus)}",
			modifier = Modifier.padding(horizontal = 3.dp),
			fontSize = 10.sp
		)
	}
}

@Composable
fun BonusNumberDescriptionCell(
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier,
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Center
	) {
		BonusNumberDescription()
	}
}

@Composable
fun LotteryResult(lottoResult: LottoResult) {
	Surface(
		modifier = Modifier
			.fillMaxHeight()
			.fillMaxWidth(),
		color = MaterialTheme.colors.primary
	) {
		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.Center
		) {
			Row(
				modifier = Modifier
					.width(getLotteryResultWidth()),
				horizontalArrangement = Arrangement.Center
			) {
				LottoBallCell(
					modifier = Modifier.weight(1f),
					number = lottoResult.no1
				)
				LottoBallCell(
					modifier = Modifier.weight(1f),
					number = lottoResult.no2
				)
				LottoBallCell(
					modifier = Modifier.weight(1f),
					number = lottoResult.no3
				)
				LottoBallCell(
					modifier = Modifier.weight(1f),
					number = lottoResult.no4
				)
				LottoBallCell(
					modifier = Modifier.weight(1f),
					number = lottoResult.no5
				)
				BonusNumberDescriptionCell(
					modifier = Modifier.weight(1f)
				)
				LottoBallCell(
					modifier = Modifier.weight(1f),
					number = lottoResult.no6
				)
			}
		}
	}
}

@Composable
fun getLotteryResultWidth() = (DisplayUtils.screenWidth.dp + dimensionResource(R.dimen.lotto_ball_width)) * (LOTTO_BALL_COUNT + 1) / (LOTTO_BALL_COUNT + 2)

@Preview
@Composable
fun LottoBallPreview1() {
	LottoBall(number = 1)
}

@Preview
@Composable
fun LottoBallPreview2() {
	LottoBall(number = 11)
}

@Preview
@Composable
fun LottoBallPreview3() {
	LottoBall(number = 21)
}

@Preview
@Composable
fun LottoBallPreview4() {
	LottoBall(number = 31)
}

@Preview
@Composable
fun LottoBallPreview5() {
	LottoBall(number = 41)
}

@Preview
@Composable
fun LottoResponsePreview() {
	val response = LottoResult(
		day = "2021-12-18",
		no1 = 1,
		no2 = 11,
		no3 = 21,
		no4 = 31,
		no5 = 41,
		no6 = 44,
		bonusNo = 45,
		lotteryNo = 1
	)

	LotteryResult(response)
}
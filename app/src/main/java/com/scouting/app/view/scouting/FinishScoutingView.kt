package com.scouting.app.view.scouting

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.scouting.app.R
import com.scouting.app.components.BasicInputField
import com.scouting.app.components.LargeHeaderBar
import com.scouting.app.components.SmallButton
import com.scouting.app.components.SpacedRow
import com.scouting.app.misc.NavDestination
import com.scouting.app.theme.AffirmativeGreen
import com.scouting.app.theme.ScoutingTheme
import com.scouting.app.utilities.composableContext
import com.scouting.app.utilities.returnTo
import com.scouting.app.viewmodel.ScoutingViewModel

@Composable
fun FinishScoutingView(navController: NavController, viewModel: ScoutingViewModel) {
    val context = composableContext
    ScoutingTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                LargeHeaderBar(
                    title = stringResource(id = R.string.finish_match_header_title),
                    navController = navController
                )
                SpacedRow(modifier = Modifier.padding(top = 50.dp)) {
                    Text(
                        text = stringResource(id = R.string.finish_match_name_confirmation),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    BasicInputField(
                        hint = stringResource(id = R.string.finish_match_name_confirmation_hint),
                        textFieldValue = viewModel.scoutName,
                        onValueChange = {
                            viewModel.scoutName = it
                        },
                        icon = painterResource(id = R.drawable.ic_user_avatar)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp, vertical = 50.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    SmallButton(
                        text = stringResource(id = R.string.finish_match_done_button_text),
                        icon = painterResource(id = R.drawable.ic_save_file),
                        contentDescription = stringResource(id = R.string.ic_save_file_content_desc),
                        onClick = {
                            if (viewModel.scoutName.text.isBlank()) {
                                Toast.makeText(
                                    context,
                                    context.getString(R.string.finish_match_scout_name_blank_toast_text),
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                viewModel.saveScoutingDataToFile(context)
                                navController.returnTo(NavDestination.HomePage)
                            }
                        },
                        color = AffirmativeGreen
                    )
                }
            }
        }
    }
}
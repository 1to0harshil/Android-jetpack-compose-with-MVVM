package Component

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ComposeWithMVVM.ui.theme.AppColor

@Composable
fun ActionBar(title: String = "TEST") {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = AppColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.displayMedium.copy(
                fontWeight = FontWeight.W400,
                fontSize = 25.sp
            ),
            color = Color.White,

            )
    }
}

@Composable
fun PrimaryButton(text: String, loading: Boolean, onClick: () -> Unit) {
    /*var isLoading by remember {mutableStateOf(false)}*/

    Button(
        onClick = onClick, colors = ButtonDefaults.buttonColors(
            containerColor = AppColor,
            contentColor = Color.White,
        ), modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp), shape = RoundedCornerShape(10.dp)
    ) {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .height(30.dp).aspectRatio(1f)
            )
        } else {
            Text(text = text)
        }
    }
}

@Composable
fun SpacerDp(spaceInDp: Int) {
    Spacer(modifier = Modifier.padding(bottom = (spaceInDp).dp))
}

fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()

}
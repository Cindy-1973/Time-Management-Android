import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.api.QuotesAPI
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import org.koin.androidx.compose.get

@Composable
fun TimerScreen(modifier: Modifier = Modifier) {
    val quotesApi: QuotesAPI = get()  // 从 Koin 容器获取 QuotesAPI

    var timeLeft by remember { mutableStateOf(25 * 60) }
    var isRunning by remember { mutableStateOf(false) }

    var quoteText by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val scope = rememberCoroutineScope()

    // 倒计时逻辑不变
    LaunchedEffect(isRunning) {
        while (isRunning && timeLeft > 0) {
            kotlinx.coroutines.delay(1000L)
            timeLeft -= 1
        }
        if (timeLeft == 0) {
            isRunning = false
            // 这里你可以添加提示音或弹窗
        }
    }

    // 启动时和每24小时刷新时拉取quote
    LaunchedEffect(Unit) {
        isLoading = true
        errorMessage = null
        try {
            val response = quotesApi.getQuote()
            if (response.isSuccessful) {
                // 解析数据示例，假设Quote有text属性
                val quote = response.body()?.quote?.body ?: "No quote"
                quoteText = quote
            } else {
                errorMessage = "Failed to load quote: ${response.code()}"
            }
        } catch (e: IOException) {
            errorMessage = "Network error: ${e.localizedMessage}"
        } catch (e: HttpException) {
            errorMessage = "HTTP error: ${e.localizedMessage}"
        } finally {
            isLoading = false
        }
    }

    val minutes = timeLeft / 60
    val seconds = timeLeft % 60

    Column(
        modifier = modifier.fillMaxSize().padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "%02d:%02d".format(minutes, seconds),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Button(
                onClick = { isRunning = true },
                enabled = !isRunning
            ) {
                Text("Start")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    isRunning = false
                    timeLeft = 25 * 60
                }
            ) {
                Text("Reset")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        when {
            isLoading -> CircularProgressIndicator()
            errorMessage != null -> Text(text = errorMessage ?: "Unknown error")
            quoteText != null -> Text(
                text = quoteText ?: "",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp),
                // 可设置居中对齐等
            )
        }
    }
}

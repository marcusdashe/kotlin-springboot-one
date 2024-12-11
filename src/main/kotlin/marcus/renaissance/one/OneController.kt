package marcus.renaissance.one

import marcus.renaissance.one.dto.Message
import marcus.renaissance.one.service.MessageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api")
class OneController(private val service: MessageService) {

    @GetMapping
    fun listMessages() = service.findMessages()

    @PostMapping
    fun post(@RequestBody message: Message): ResponseEntity<Message>{
        val savedMessage = service.save(message);
        return ResponseEntity.created(URI("/${savedMessage.id}")).body(savedMessage)
    }
    @GetMapping("/index")
    fun index(@RequestParam("name") name: String) = "Hello, $name!"

    @GetMapping("/messages")
    fun listMessages_() = listOf<Message>(
        Message("1", "Ina Kwana"),
        Message("2", "Bit pan"),
        Message("3", "Bonjour"),
    )
}
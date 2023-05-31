import com.SeharSana.HMS.entity.Guest
import com.SeharSana.HMS.service.GuestService
import com.fasterxml.jackson.databind.ObjectMapper
import com.SeharSana.HMS.model.GuestModel
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class GuestControllerSpec extends Specification
{
    def mockGuestService = Mock(GuestService.class)
    def controller = new GuestController(guestService: mockGuestService)
    def mockMvc = MockMvcBuilders.standaloneSetup(controller).build()

    /*
List< MakeModel> makeModelList = new ArrayList<>();
MakeModel makeModel = new MakeModel()
makeModel.setMakeId(1L)
makeModel.setMakeName("Audi")
makeModelList.add(makeModel)
*/
    //def objMakeModel = new MakeModel(makeId: 1L, makeName: "Audi")

    def setupGuestResponse()
    {
        [new GuestModel(id: 1L, email: "shari@gmail.com"), new GuestModel(id: 2L, email: "xyz@gmail.com")]
    }

    def "GET: / verify endpoint returns list of GuestModel object"()
    {
        given:
        mockGuestService.getGuest(id email) >> guestResponse
        when:
        def response = mockMvc.perform(get(inputParam).contentType(MediaType.APPLICATION_JSON_VALUE))

        then:
        response.andExpect(status().isOk())
        response.andExpect {content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)}
        response.andExpect {MockMvcResultMatchers.jsonPath(('$[0].id' ).value(2L)
                ,('$[0].email').formatted("shari@gmail.com"))}


        where:
        inputParam                                |   id           |     email             |     guestResponse
        "/guest/list"                             |   null         |      null             |    setupGuestResponse()
        "/guest/list?id=1&&email=shari@gmail.com" |   1L           |    "shari@gmail.com"  |      setupGuestResponse()
    }


    def "GET: / verify endpoint returns null value"()
    {
        given:
        mockGuestService.getGuest(_) >> null
        when:
        def response = mockMvc.perform(get("/guest/list").contentType(MediaType.APPLICATION_JSON_VALUE))

        then:
        response.andExpect(status().isOk())
        response.andExpect {content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)}
        response.andExpect {MockMvcResultMatchers.jsonPath('$.id').value(null)}
    }

    def "POST: / Verify make saves succesfully"()
    {
        given:
        def guestModel = new GuestModel(email: "shari@gmail.com")
        def guestModelJson = new ObjectMapper().writeValueAsString(guestModel)
        mockGuestService.saveGuest(guestModel) >> new GuestModel(id: 1L, email: "shari@gmail.com" )
        when:
        def response = mockMvc.perform(post("/guest/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(guestModelJson))

        then: "status should be 200"
        response.andExpect(status().isOk())
    }
}


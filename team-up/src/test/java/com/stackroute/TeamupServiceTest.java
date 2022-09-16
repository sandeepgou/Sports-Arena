/*
package com.stackroute;

import com.stackroute.Entity.Teamup;
import com.stackroute.constantEnums.RequestStatus;
import com.stackroute.repository.TeamupRepository;
import com.stackroute.service.TeamupServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamupServiceTest {

	@Autowired
	private TeamupServiceImpl teamupService;

	@MockBean
	private TeamupRepository teamupRepository;

	Teamup teamup = new Teamup("121", "swastik@gmail.com", "kumar@gmail.com", RequestStatus.PENDING, LocalDate.now());

	@Test
	public void sendInvitesTest() throws Exception {
		when(teamupRepository.save(teamup)).thenReturn(teamup);
		assertEquals(teamup, teamupService.sendInvites(teamup));
	}

	@Test
	public void updateInviteTest() throws Exception {
		when(teamupRepository.findByRequestId("121")).thenReturn(teamup);
		assertEquals("PENDING", teamupService.updateInvite("121", RequestStatus.PENDING));
	}

	@Test
	public void getRecievedInviatationsListTest() throws Exception {
		when(teamupRepository.findByRequestedPlayerEmail("kumar@gmail.com")).
				thenReturn(Stream.of(new Teamup(
						"121,", "swastik@gmail.com", "kumar@gmail.com", RequestStatus.REJECTED, LocalDate.now())).
						collect(Collectors.toList()));
		assertEquals(1, teamupService.getRecievedInvitations("kumar@gmail.com").size());
	}

	@Test
	public void getSentInviatationsListTest() throws Exception {
		when(teamupRepository.findBySenderEmail("swastik@gmail.com")).
				thenReturn(Stream.of(new Teamup(
								"121,", "swastik@gmail.com", "kumar@gmail.com", RequestStatus.REJECTED, LocalDate.now())).
						collect(Collectors.toList()));
		assertEquals(1, teamupService.getSentInvitations("swastik@gmail.com").size());
	}

	@Test
	public void deleteRequestTestTest() {
		Teamup teamup =  new Teamup("121,", "swastik@gmail.com", "kumar@gmail.com", RequestStatus.REJECTED, LocalDate.now());
		teamupService.deleteRequest("121");
		verify(teamupRepository, times(1)).deleteById("121");
	}
}
*/

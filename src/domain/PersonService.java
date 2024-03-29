package domain;

import java.util.List;
import java.util.Set;

import controller.ChatSession;
import db.ChatSessionRepository;
import db.ChatSessionRepositoryInMemory;
import db.PersonRepository;
import db.PersonRepositoryStub;

public class PersonService {
	private PersonRepository personRepository = new PersonRepositoryStub();
	private ChatSessionRepository chatSessionRepository = new ChatSessionRepositoryInMemory();

	public PersonService(){
	}
	
	public Person getPerson(String personId)  {
		return getPersonRepository().get(personId);
	}

	public List<Person> getPersons() {
		return getPersonRepository().getAll();
	}

	public void addPerson(Person person) {
		getPersonRepository().add(person);
	}

	public void updatePersons(Person person) {
		getPersonRepository().update(person);
	}

	public void deletePerson(String id) {
		getPersonRepository().delete(id);
	}
	
	public Person getAuthenticatedUser(String email, String password) {
		return getPersonRepository().getAuthenticatedUser(email, password);
	}

	private PersonRepository getPersonRepository() {
		return personRepository;
	}

	public ChatSession getChatSession(Set<Person> people) {
		return chatSessionRepository.getChatSession(people);
	}
}

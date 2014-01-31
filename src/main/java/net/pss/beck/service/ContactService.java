package net.pss.beck.service;

import java.util.List;
import net.pss.beck.domain.Contact;

public interface ContactService {

	public void addContact(Contact contact);

	public List<Contact> listContact();

	public void removeContact(Integer id);
}

package net.tap5.vote1.pages;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import net.tap5.vote1.entities.Item;

/**
 * Start page of application vote1.
 */
public class Index
{	
	@Property
    private Item newItem;
	
	@Property
    private Item item;
	
	@Inject
	private Session session;
	
	@Component
	private BeanEditForm form;
	
	@Component
	private Zone listZone;
	
	@CommitAfter
	public void onSuccess() {
		session.persist(newItem);
	}
	
	public List<Item> getItems() {
		return session.createCriteria(Item.class).addOrder(Order.desc("votes")).list();
	}
	
	public void onValidate() {
		try {
			URL url = new URL(newItem.getUrl());
		} catch (MalformedURLException e) {
			form.recordError("Invalid URL");
		}
	}
	
	@CommitAfter
	public Object onActionFromVote(Item i) {
		i.setVotes(i.getVotes() + 1);
		session.persist(i);
		return listZone.getBody();
	}
	
    public Date getCurrentTime()
    {
        return new Date();
    }
}

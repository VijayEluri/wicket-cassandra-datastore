package org.wicketstuff.datastore.cassandra.demo;

import org.apache.wicket.DefaultPageManagerProvider;
import org.apache.wicket.Page;
import org.apache.wicket.pageStore.IDataStore;
import org.apache.wicket.protocol.http.WebApplication;
import org.wicketstuff.datastore.cassandra.CassandraDataStore;
import org.wicketstuff.datastore.cassandra.CassandraSettings;
import org.wicketstuff.datastore.cassandra.ICassandraSettings;

public class DemoApplication extends WebApplication
{
	@Override
	public Class<? extends Page> getHomePage()
	{
		return HomePage.class;
	}

	@Override
	public void init()
	{
		super.init();
		
		mountPage("page1", HomePage.class);
		mountPage("page2", Page2.class);

		// for testing #getData().
		getStoreSettings().setInmemoryCacheSize(1);

		setPageManagerProvider(new DefaultPageManagerProvider(this)
		{
			@Override
			protected IDataStore newDataStore()
			{
				ICassandraSettings settings = new CassandraSettings();
				settings.getContactPoints().add("localhost");
				return new CassandraDataStore(settings);
			}
		});
	}
}

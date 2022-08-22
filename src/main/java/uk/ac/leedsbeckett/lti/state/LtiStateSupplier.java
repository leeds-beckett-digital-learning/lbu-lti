/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.lti.state;

import uk.ac.leedsbeckett.lti.config.ClientLtiConfigurationKey;

/**
 * This is required because of the detail of generics in Java.
 * This is because it is hard to call a generic class's constructor.
 * 
 * @author jon
 * @param <T>
 */
public interface LtiStateSupplier<T extends LtiState>
{
  /**
   * Implementations will instantiate a subclass of LtiState and
   * supply it to the caller.
   * 
   * @param clientKey
   * @return 
   */
  public T get( ClientLtiConfigurationKey clientKey );
}

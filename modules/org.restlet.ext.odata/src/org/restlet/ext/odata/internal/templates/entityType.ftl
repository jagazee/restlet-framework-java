/**
 * Copyright 2005-2010 Noelios Technologies.
 * 
 * The contents of this file are subject to the terms of one of the following
 * open source licenses: LGPL 3.0 or LGPL 2.1 or CDDL 1.0 or EPL 1.0 (the
 * "Licenses"). You can select the license that you prefer but you may not use
 * this file except in compliance with one of these Licenses.
 * 
 * You can obtain a copy of the LGPL 3.0 license at
 * http://www.opensource.org/licenses/lgpl-3.0.html
 * 
 * You can obtain a copy of the LGPL 2.1 license at
 * http://www.opensource.org/licenses/lgpl-2.1.php
 * 
 * You can obtain a copy of the CDDL 1.0 license at
 * http://www.opensource.org/licenses/cddl1.php
 * 
 * You can obtain a copy of the EPL 1.0 license at
 * http://www.opensource.org/licenses/eclipse-1.0.php
 * 
 * See the Licenses for the specific language governing permissions and
 * limitations under the Licenses.
 * 
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly at
 * http://www.noelios.com/products/restlet-engine
 * 
 * Restlet is a registered trademark of Noelios Technologies.
 */

package ${packageName};

<#if entityType.blob>import org.restlet.data.Reference;</#if>
<#compress>
<#list entityType.importedJavaClasses?sort as clazz>
import ${clazz};
</#list>

<#list entityType.importedEntityTypes?sort as type>
import ${type.fullClassName};
</#list>
</#compress>


<#compress>
/**
 * Generated by the generator tool for the OData extension for the Restlet framework.<br>
 * 
<#if metadata.metadataRef??> * @see <a href="${metadata.metadataRef}">Metadata of the target OData service</a></#if>
 * 
 */
</#compress>

public <#if entityType.abstractType>abstract </#if>class ${className} {

<#compress>
<#list entityType.properties?sort_by("name") as property>
    private ${property.type.javaType} ${property.normalizedName}<#if property.defaultValue??> = property.defaultValue</#if>;
</#list>
<#list entityType.associations?sort_by("name") as association>
    <#if association.toRole.toMany>private List<${association.toRole.type.className}> ${association.toRole.normalizedRole};<#else>private ${association.toRole.type.className} ${association.toRole.normalizedRole};</#if>
</#list>

<#if entityType.blob>
    /** The reference of the underlying blob representation. */
    private Reference ${entityType.blobValueRefProperty.name};
</#if>
</#compress>
    /**
     * Constructor without parameter.
     * 
     */
    public ${className}() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param id
     *            The identifiant value of the entity.
     */
    public ${className}(<#if entityType.keys??><#list entityType.keys as key>${key.type.javaType} ${key.normalizedName}<#if key_has_next>, </#if></#list></#if>) {
        this();
<#if entityType.keys??><#list entityType.keys as key>
        this.${key.normalizedName} = ${key.normalizedName};
</#list></#if>
    }
    
<#list entityType.properties?sort_by("name") as property>
   /**
    * Returns the value of the ${property.normalizedName} attribute.
    *
    * @return The value of the ${property.normalizedName} attribute.
    */
   <#if property.getterAccess??>${property.getterAccess}<#else>public</#if> ${property.type.javaType} get${property.normalizedName?cap_first}() {
      return ${property.normalizedName};
   }
   
</#list>
<#list entityType.associations?sort_by("name") as association>
   /**
    * Returns the value of the ${association.toRole.normalizedRole} attribute.
    *
    * @return The value of the ${association.toRole.normalizedRole} attribute.
    */
    <#if association.toRole.toMany>
   public List<${association.toRole.type.className}> get${association.toRole.normalizedRole?cap_first}() {
    <#else>
   public ${association.toRole.type.className} get${association.toRole.normalizedRole?cap_first}() {
    </#if>
      return ${association.toRole.normalizedRole};
   }
   
</#list>
<#if entityType.blob>
   /**
    * Returns the @{Link Reference} of the underlying blob.
    *
    * @return The @{Link Reference} of the underlying blob.
    */
   public Reference get${entityType.blobValueRefProperty.name?cap_first}() {
      return ${entityType.blobValueRefProperty.name};
   }
</#if>

<#list entityType.properties?sort_by("name") as property>
   /**
    * Sets the value of the ${property.normalizedName} attribute.
    *
    * @param ${property.name}
    *     The value of the ${property.normalizedName} attribute.
    */
   <#if property.setterAccess??>${property.setterAccess}<#else>public</#if> void set${property.normalizedName?cap_first}(${property.type.javaType} ${property.normalizedName}) {
      this.${property.normalizedName} = ${property.normalizedName};
   }
   
</#list>
<#list entityType.associations?sort_by("name") as association>
   /**
    * Sets the value of the ${association.toRole.normalizedRole} attribute.
    *
    * @param ${association.toRole.normalizedRole}
    *     The value of the ${association.toRole.normalizedRole} attribute.
    */
    <#if association.toRole.toMany>
   public void set${association.toRole.role?cap_first}(List<${association.toRole.type.className}> ${association.toRole.normalizedRole}) {
    <#else>
   public void set${association.toRole.role?cap_first}(${association.toRole.type.className} ${association.toRole.normalizedRole}) {
    </#if>
      this.${association.toRole.normalizedRole} = ${association.toRole.normalizedRole};
   }

</#list>
<#if entityType.blob>
   /**
    * sets the @{Link Reference} of the underlying blob.
    *
    * @param ref
    *     The @{Link Reference} of the underlying blob.
    */
   public void set${entityType.blobValueRefProperty.name?cap_first}(Reference ref) {
      this.${entityType.blobValueRefProperty.name} = ref;
   }
</#if>

}
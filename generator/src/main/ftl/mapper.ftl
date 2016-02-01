<#noparse><?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="</#noparse>${entityClassName}<#noparse>">
	<cache type="org.mybatis.caches.ehcache.EhcacheCache"
		flushInterval="86400" />
	<resultMap type="</#noparse>${entityClassName}<#noparse>" id="</#noparse>${entityClassSimpleName}<#noparse>Map" extends="BaseEntity.baseEntityMap"></#noparse>
		<#list childClassFields as field>
		<result property="${field.fieldName}" column="${field.columnName}" jdbcType="${field.jdbcType}" ${field.javaTypeDeclare} />
		</#list>
	<#noparse>
	</resultMap>
	<insert id="insert" parameterType="</#noparse>${entityClassName}<#noparse>" useGeneratedKeys="true" flushCache="true" keyProperty="id">
	    <![CDATA[
	     insert into </#noparse>${tableName}<#noparse>
	     ]]>
		<trim prefix="(" suffix=")" prefixOverrides=",">
			</#noparse>
			<#list childClassFields as field>
			<if test="${field.fieldName} != null">
				,`${field.columnName}`
			</if>
			</#list>
			<#noparse>
			<!-- 以下是所有BaseEntity公用的 -->
			<if test="insertTime != null">
				,`insert_time`
			</if>
			<if test="lastUpdateTime != null">
				,`last_update_time`
			</if>
			<if test="visible != null">
				,`visible`
			</if>
			<if test="status != null">
				,`status`
			</if>
		</trim>
	     <![CDATA[
	      values
	     ]]>
		<trim prefixOverrides="," suffix=")" prefix="(">
			</#noparse>
			<#list childClassFields as field>
			<if test="${field.fieldName} != null">
				,<#noparse>#{</#noparse>${field.fieldName}}
			</if>
			</#list>
			<#noparse>
			<!-- 以下是所有BaseEntity公用的 -->
			<if test="insertTime != null">
				, #{insertTime}
			</if>
			<if test="lastUpdateTime != null">
				, #{lastUpdateTime}
			</if>
			<if test="visible != null">
				, #{visible}
			</if>
			<if test="status != null">
				, #{status}
			</if>
		</trim>
	</insert>
	<update id="update" parameterType="</#noparse>${entityClassName}<#noparse>" flushCache="true">
	    <![CDATA[
	     update </#noparse>${tableName}<#noparse> t
	    ]]>
		<set>
			<trim suffixOverrides=",">
				</#noparse>
				<#list childClassFields as field>
				<if test="${field.fieldName} != null">
					${field.columnName}=<#noparse>#{</#noparse>${field.fieldName}},
				</if>
				</#list>
				<#noparse>
				<if test="lastUpdateTime != null">
					last_update_time=#{lastUpdateTime},
				</if>
				<if test="status != null">
					status=#{status},
				</if>
				<if test="lastUpdateTime != null">
					visible=#{visible},
				</if>
			</trim>
		</set>
		<where>
		    <choose>
		        <when test="id != null">
		            t.id=#{id}
		        </when>
		        <otherwise>
		            1=2
		        </otherwise>
		    </choose>
		</where>
	</update>
	<delete id="delete" parameterType="java.lang.Long" flushCache="true">
	    <![CDATA[
	     delete from </#noparse>${tableName}<#noparse>
	      where id=#{id}
	    ]]>
	</delete>
	<select id="findById" parameterType="java.lang.Long" resultMap="</#noparse>${entityClassSimpleName}<#noparse>Map">
	    <![CDATA[
	     select * from </#noparse>${tableName}<#noparse> t
	      where t.id=#{id}
	    ]]>
	</select>
	<select id="findAll" resultMap="</#noparse>${entityClassSimpleName}<#noparse>Map">
	    <![CDATA[
	     select * from </#noparse>${tableName}<#noparse> t
	    ]]>
	</select>
</mapper>
</#noparse>
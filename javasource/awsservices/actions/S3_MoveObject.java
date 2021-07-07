// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package awsservices.actions;

import com.mendix.core.CoreException;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.webui.CustomJavaAction;
import awsservices.impl.AWSClients;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CopyObjectRequest;
import software.amazon.awssdk.services.s3.model.CopyObjectResponse;
import software.amazon.awssdk.services.s3.model.CopyObjectResult;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;

public class S3_MoveObject extends CustomJavaAction<java.lang.Boolean>
{
	private IMendixObject __credentials;
	private awsservices.proxies.Credentials credentials;
	private java.lang.String sourceBucket;
	private java.lang.String sourceKey;
	private java.lang.String destinationBucket;
	private java.lang.String destionationKey;

	public S3_MoveObject(IContext context, IMendixObject credentials, java.lang.String sourceBucket, java.lang.String sourceKey, java.lang.String destinationBucket, java.lang.String destionationKey)
	{
		super(context);
		this.__credentials = credentials;
		this.sourceBucket = sourceBucket;
		this.sourceKey = sourceKey;
		this.destinationBucket = destinationBucket;
		this.destionationKey = destionationKey;
	}

	@java.lang.Override
	public java.lang.Boolean executeAction() throws Exception
	{
		this.credentials = __credentials == null ? null : awsservices.proxies.Credentials.initialize(getContext(), __credentials);

		// BEGIN USER CODE
		S3Client client = AWSClients.getS3Client(getContext(), credentials);
		CopyObjectRequest req1 = CopyObjectRequest.builder()
					.copySource(sourceBucket + "/" + sourceKey)
					.destinationBucket(destinationBucket)
					.destinationKey(destionationKey)
					.build();
		CopyObjectResponse res1 = client.copyObject(req1);
		CopyObjectResult cor1 = res1.copyObjectResult();
		if (cor1 == null)
			throw new CoreException("No copy result.");
		
		DeleteObjectRequest req = DeleteObjectRequest.builder()
				.bucket(sourceBucket)
				.key(sourceKey)
				.build();
		client.deleteObject(req);
		
		return true;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "S3_MoveObject";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}

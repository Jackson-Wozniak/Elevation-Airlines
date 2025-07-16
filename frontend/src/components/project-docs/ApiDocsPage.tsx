import Page from '../shared/Page';
import Sidebar from '../shared/SideBar';
import Container from '../shared/Container';
import ApiDocs from './ApiEndpointSection';

function ApiDocsPage(){
    return (
        <Page>
            <Sidebar />
            <Container>
                <ApiDocs
  returnTypes={[
    {
      name: "User",
      type: "object",
      sample: { id: 1, name: "Alice", email: "alice@example.com" }
    },
    {
      name: "Error",
      type: "object",
      sample: { error: "Not Found", code: 404 }
    }
  ]}
  endpoints={[
    {
      method: "GET",
      url: "/api/users",
      statusCode: 200,
      parameters: { limit: "number - max users to return" },
      returnType: "User[]",
      returnSample: [{ id: 1, name: "Alice", email: "alice@example.com" }]
    },
    {
      method: "POST",
      url: "/api/users",
      statusCode: 201,
      parameters: {},
      requestBody: { name: "string", email: "string" },
      returnType: "User",
      returnSample: { id: 2, name: "Bob", email: "bob@example.com" }
    }
  ]}
/>
            </Container>
        </Page>
    )
}

export default ApiDocsPage;
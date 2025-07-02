import '../../styles/shared.css';

function Page({ children }: { children?: React.ReactNode }){
    return (
        <div className="page-container">{children}</div>
    )
}

export default Page;
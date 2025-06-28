import '../../styles/shared.css';

function Container({ children }: { children?: React.ReactNode }){
    return (
        <div className="content-container">{children}</div>
    )
}

export default Container;